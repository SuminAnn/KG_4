package bucket.kurly.user.goods.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.Response;

import bucket.kurly.user.goods.Goods_CartShowVO;
import bucket.kurly.user.goods.Goods_CartVO;
import bucket.kurly.user.goods.Goods_SellVO;
import bucket.kurly.user.goods.service.GoodsService;
import bucket.kurly.user.member.MemberVO;
import bucket.kurly.user.member.service.MemberService;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private MemberService memberService;
	// 상품 리스트, 상품 카운트, 페이징
	@RequestMapping("/goods_list.do")
	public String getGoods_list(Model model, @RequestParam("type") String select_type) {		
		System.out.println("상품 목록 요청");
		
		List<Goods_SellVO> goods_sell_list = goodsService.selectGoods_sell(select_type);
		System.out.println(goods_sell_list);
		
//		String itemCnt = goodsService.getGoods_cnt(); // 아이템 개수
//		Pagination pagination = new Pagination(); //Pagination 객체 생성
//		pagination.pageInfo(page, range, itemCnt);
		
		String A = null;
		if(select_type.equals("new")){
			A = "신상품순";
		}else {
			
		}

		model.addAttribute("goods_sell_list", goods_sell_list); // 상품DB품목
		model.addAttribute("itemCnt", goods_sell_list.size()); // 상품카운트
		model.addAttribute("select_type",A); // 상품카운트
//		model.addAttribute("pagination", pagination); //페이징
		return "goods/goods_list";
	}


	// 상품 상세페이지
	@RequestMapping("/goods_list_detail.do")
	public String getGoods_list_detail(Model model, @RequestParam("goods_sell_no") int goods_sell_no) {

		Goods_SellVO goods_sellVO = goodsService.getGoods_detail(goods_sell_no);
		model.addAttribute("goods_sellVO", goods_sellVO);
	
		return "goods/goods_list_detail";
	}
	
	
	//장바구니에 담기
	@RequestMapping("/insertGoods_cart.do")
	public String insertGoods_cart(@RequestParam(value="goods_sell_no",required=false) int goods_sell_no,@RequestParam("count") int count, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		
		String member_id = (String) session.getAttribute("id");
		
		//if 걸어주기
		if(member_id == null){
			Cookie cookie = new Cookie(String.valueOf(goods_sell_no), String.valueOf(count));
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		
		}else{
			Goods_CartVO vo = new Goods_CartVO();
			vo.setGoods_cart_count(count);
			vo.setGoods_cart_sell_no(goods_sell_no);
			vo.setGoods_cart_member_no((int)session.getAttribute("member_no"));
			
			System.out.println(goods_sell_no);
			System.out.println(count);
			goodsService.insertGoods_cart(vo);
		}
		String path = "/goods_list_detail.do?goods_sell_no="+goods_sell_no;
		 return "redirect:"+ path;
	}
	
	//장바구니 정보
	@RequestMapping("/getGoods_cart.do")
	public String getGoods_cart(Model model, HttpSession session, HttpServletRequest request) {
		String member_id = (String) session.getAttribute("id");
		List<Goods_CartShowVO> goods_cartShowVO = new ArrayList<Goods_CartShowVO>();
		
		if(member_id == null) {
			Cookie[] cookies = request.getCookies();
			String cName="";
			String cValue="";
			
			if(cookies != null){
			    for(int i=0; i < cookies.length; i++){
			           cName = cookies[i].getName();
			           if(cName.length() < 4) {
			        	   cValue = cookies[i].getValue();
			        	   Goods_SellVO sellvo = goodsService.getGoods_detail(Integer.parseInt(cName));
			        	   Goods_CartShowVO showvo = new Goods_CartShowVO();
			        	
			        	   showvo.setGoods_sell_no(Integer.parseInt(cName));
			        	   showvo.setCategory_goods_image_thumb(sellvo.getGoodsvo().getCategory_goods_image_thumb());
			        	   showvo.setCategory_goods_name(sellvo.getGoodsvo().getCategory_goods_name());
			        	   showvo.setCategory_goods_packaging_type(sellvo.getGoodsvo().getCategory_goods_packaging_type());
			        	   showvo.setGoods_sell_price(sellvo.getGoods_sell_price());
			        	   showvo.setGoods_cart_count(Integer.parseInt(cValue));
			        	   
			        	   goods_cartShowVO.add(showvo);
			           }
			        }
			    System.out.println("=============================");
		       System.out.println(goods_cartShowVO);
			    model.addAttribute("goods_cartShowVO", goods_cartShowVO);
			    }
			
			return "goods/goods_cart_nonmember";
		}else {
			MemberVO memberVO = memberService.selectMember(member_id);
			System.out.println(memberVO);
			
			goods_cartShowVO = goodsService.getGoods_cart((int)session.getAttribute("member_no")); //세션으로
			//Integer countGoods_cart = goodsService.countGoods_cart(gcvo);
			
			model.addAttribute("memberVO",memberVO);
			model.addAttribute("goods_cartShowVO", goods_cartShowVO);
			//model.addAttribute("countGoods_cart",countGoods_cart);
			
			return "goods/goods_cart";
		}
	}
	//장바구니 삭제
	@ResponseBody
	@RequestMapping("/deleteGoods_cart.do")
	public String deleteGoods_cart(Goods_CartVO gsvo, HttpServletRequest request) {
				
		String id = request.getParameter("goods_cart_no");
		System.out.println(id);
		//System.out.println(gcvo.getGoods_cart_no());
		goodsService.deleteGoods_cart(gsvo);
		
		return null;
	}
	
	//장바구니 비회원 삭제
	@ResponseBody
	@RequestMapping("/deleteGoods_cart_nonmember.do")
	public String deleteGoods_cart_nonmember(@RequestParam("goods_sell_no") int goods_sell_no, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(goods_sell_no);
		Cookie[] cookies = request.getCookies();
		String cName="";


		if(cookies != null){
		    for(int i=0; i < cookies.length; i++){
		           cName = cookies[i].getName();
		           if(cName.equals(String.valueOf(goods_sell_no))){
		        	   cookies[i].setMaxAge(0);
			           response.addCookie(cookies[i]);
		        	   System.out.println("쿠키삭제");
		         }
		    }
		}  
		return null;
	}
	
	@RequestMapping("/test.do")
	public String test() {
		
		return "goods/test";
		
	}
			
			
	
}