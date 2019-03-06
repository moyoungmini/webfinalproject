package Java;

public class ListUser {
	private String id;
	private String pw;
	private String name;
	private String address;

	public ListUser() {
		id = "";
		pw = "";
		name = "";
		address = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
// 유저의 정보를 담는 클래스