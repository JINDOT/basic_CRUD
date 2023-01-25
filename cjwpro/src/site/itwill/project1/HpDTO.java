package site.itwill.project1;

public class HpDTO {
	private int no;
	private String name;
	private String phone;
	private String address;
	private String birth;
	private int docno;
	
	public HpDTO() {
		// TODO Auto-generated constructor stub
	}

	public HpDTO(int no, String name, String phone, String address, String birth, int docno) {
		super();
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birth = birth;
		this.docno = docno;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getDocno() {
		return docno;
	}

	public void setDocno(int docno) {
		this.docno = docno;
	}
	
	
	
	

}
