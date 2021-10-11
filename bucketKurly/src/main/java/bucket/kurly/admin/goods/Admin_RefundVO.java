package bucket.kurly.admin.goods;


public class Admin_RefundVO {
	
	private int refund_no;
	private String refund_order_no;
	private String refund_import_no;
	private String refund_member_id;
	private int refund_price;
	private int refund_state;
	
	public int getRefund_no() {
		return refund_no;
	}
	public void setRefund_no(int refund_no) {
		this.refund_no = refund_no;
	}
	public String getRefund_order_no() {
		return refund_order_no;
	}
	public void setRefund_order_no(String refund_order_no) {
		this.refund_order_no = refund_order_no;
	}
	public String getRefund_import_no() {
		return refund_import_no;
	}
	public void setRefund_import_no(String refund_import_no) {
		this.refund_import_no = refund_import_no;
	}
	public String getRefund_member_id() {
		return refund_member_id;
	}
	public void setRefund_member_id(String refund_member_id) {
		this.refund_member_id = refund_member_id;
	}
	public int getRefund_price() {
		return refund_price;
	}
	public void setRefund_price(int refund_price) {
		this.refund_price = refund_price;
	}
	public int getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(int refund_state) {
		this.refund_state = refund_state;
	}
	@Override
	public String toString() {
		return "Admin_RefundVO [refund_no=" + refund_no + ", refund_order_no=" + refund_order_no + ", refund_import_no="
				+ refund_import_no + ", refund_member_id=" + refund_member_id + ", refund_price=" + refund_price
				+ ", refund_state=" + refund_state + "]";
	}
	
	
}
