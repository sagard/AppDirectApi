package classes;

public class Item {
	String name;
	String desc;
	int price;
	int qty;
	
	public Item(String name,String desc,int price,int qty){
		this.name = name;
		this.desc = desc;
		this.price=price;
		this.qty=qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	

}
