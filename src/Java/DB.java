package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Java.DB;

public class DB {
	private Connection con;// DB�� ���� �ϱ� ���� ��ü
	private Statement stmt; // statement ��ü
	private ResultSet rs;// ������ ��� ��ü (Select�� ��� ����� �����Ѵ�.)

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// ����̹� �ε�: DriverManager�� ���
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}

		try {
			String jdbcUrl = "jdbc:mysql://localhost:3306/inp?autoReconnect=true&useSSL=false";// ����ϴ� �����ͺ��̽����� ������ url
			String userId = "root";// ����ڰ���
			String userPass = "root";// ����� �н�����

			con = DriverManager.getConnection(jdbcUrl, userId, userPass);// Connection ��ü�� ��
			stmt = con.createStatement();// Statement ��ü�� ��

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}
	//DB Connect

	public boolean selectQuery(String sql) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertQuery(String sql) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateQuery(String sql) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteQuery(String sql) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// Insert Update Select Delete ������ ��� sql���� ������ ������ con.createStatement�� �ڵ尡
	// �ߺ��ǹǷ� �Լ��� ���� �и��Ͽ���.

	public boolean login(String id, String pw) {
		String sql = "select * from user where ID = '" + id + "';";
		selectQuery(sql);
		try {
			while (rs.next()) {
				String dbPw = rs.getString("PW");
				if (dbPw.equals(pw)) {
					return true;
				}
			}
			// ID/pW ��ġ�� ��� �α��� ����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean idOverlap(String id) {
		String sql = "select * from user;";
		selectQuery(sql);
		try {
			while (rs.next()) {
				if (id.equals(rs.getString("ID"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// User Table���� �ش� ���̵� �����ϴ��� ��ȸ�� �Ѵ�.

	public boolean nameOverlap(String name) {
		String sql = "select * from user;";
		selectQuery(sql);
		try {
			while (rs.next()) {
				if (name.equals(rs.getString("NAME"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// User Table���� �ش� �г����� �����ϴ��� ��ȸ�� �Ѵ�.

	public boolean signUP(String id, String pw1, String pw2, String name, String address) {
		if (!pw1.equals(pw2) || id.equals("") || pw1.equals("") || name.equals("") || address.equals("") || idOverlap(id) || nameOverlap(name)) {
			return false;
		} else {
			String sql = "insert into user values('" + id + "','" + pw1 + "','" + name + "','" + address + "');";
			insertQuery(sql);
			return true;
		}
	}
	// ȸ�����Խ� User ���̺� ����
	public boolean update(String id, String pw1, String pw2, String name, String address) {
		if (!pw1.equals(pw2) || pw1.equals("") || name.equals("") || name.equals("") || address.equals("")) {
			return false;
		}
		// ID,PW,Name,Address�� �ϳ��� �� ��� ���� ó���� �Ѵ�.
		String sql = "update user set pw='" + pw1 + "', name='" + name + "', address='" + address + "' where id='" + id
				+ "';";
		updateQuery(sql);
		return true;
	}
	// change.jsp���� ȸ������ ������ ���� ������Ʈ�� �Ҷ� ����ϴ� �������̴�.

	public String getDate() throws SQLException {
		String formatType = "yyyy-MM-dd-hh-mm-ss";
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat(formatType);
		String nowTime = dayTime.format(new Date(time));
		return nowTime;
	}
	// ���� �ð��� �θ��� �Լ��̴�.
	
	public int getNo() throws SQLException {
		String sql = "select no from bbs order by no desc limit 1;";
		selectQuery(sql);
		int no = 0;
		while (rs.next()) {
			no = Integer.parseInt(rs.getString("no"));
		}
		return no + 1;
	}
	// �Խ��� �� �ۼ� �� bbs ���̺��� ������ no���� +1 �� ����

	public boolean writeContent(int no, String title, String writer, String date, String content) throws SQLException {
		String sql = "select * from bbs where no='" + no + "';";
		selectQuery(sql);
		while (rs.next()) {
			sql = "update bbs set title='" + title + "', writer = '" + writer + "', date = '" + date + "', content='"
					+ content + "' where no='" + no + "';";
			updateQuery(sql);
			return true;
		} // ������ �����ϴ� �Խ��Ǳ��� ��� update ����

		sql = "insert into bbs values('" + no + "','" + title + "','" + writer + "','" + date + "','" + content + "');";
		if (insertQuery(sql)) {
			return true;
		} else {
			return false;
		} // ���ο� ���� �ۼ� �� ��� insert ����
	}

	public ArrayList<List> getList() throws SQLException {
		String sql = "select * from bbs order by no desc;";
		selectQuery(sql);

		ArrayList<List> tmp = new ArrayList<List>();
		while (rs.next()) {
			List listTmp = new List();
			listTmp.setContent(rs.getString("CONTENT"));
			listTmp.setDate(rs.getString("DATE"));
			listTmp.setNo(rs.getInt("NO"));
			listTmp.setTitle(rs.getString("TITLE"));
			listTmp.setWriter(rs.getString("WRITER"));
			tmp.add(listTmp);
		}
		return tmp;
	}
	// BBS Table���� �ش� ������ �о� ���� listTmp�� ���� ���� listTmp�� tmp �߰��Ͽ� ArrayList�� �����.
	// DB�� �����ϴ� ��� ������ �о�� �� ArrayList�� ��ȯ�Ѵ�.

	public String[] getView(int no) throws SQLException {
		String sql = "select * from bbs where no = '" + no + "';";
		selectQuery(sql);
		while (rs.next()) {
			String title = rs.getString("title");
			String writer = rs.getString("writer");
			String date = rs.getString("date");
			String content = rs.getString("content");
			String[] tmp = new String[] { title, writer, date, content };
			return tmp;
		}
		return null;
	}
	// �ش� no�� ���� ���ڵ带 ���� ������ ������ ������ �����Ѵ�.

	public boolean removeList(int no) {
		String sql = "delete from bbs where no='" + no + "';";
		if (deleteQuery(sql)) {
			return true;
		} else {
			return false;
		}
	}
	// �Խ��ǿ��� �ش� no�� �����Ҷ� ����Ѵ�.

	public ArrayList<ListUser> getUser() throws SQLException {
		String sql = "select * from user;";
		selectQuery(sql);

		ArrayList<ListUser> tmp = new ArrayList<ListUser>();
		while (rs.next()) {
			ListUser listTmp = new ListUser();
			listTmp.setId(rs.getString("id"));
			listTmp.setName(rs.getString("name"));
			listTmp.setPw(rs.getString("pw"));
			listTmp.setAddress(rs.getString("address"));
			tmp.add(listTmp);
		}
		return tmp;
	}
	//�������� ������������������ ������ �ش��ϴ� ����Ʈ���� ������ ����Ѵ�.

	public boolean userDel(String id) {
		String sql = "delete from user where id='" + id + "';";
		if (id.equals("root")) {
			return false;
		}
		if (deleteQuery(sql)) {
			return true;
		} else {
			return false;
		}
	}
	// �����ڰ� user�� ������ �� ����Ѵ�.
	public ArrayList<List> selectList(String set,String value) throws SQLException {
		String sql = "select * from bbs order by no desc where "+set+"='"+value+"';";
		sql="select * from bbs where "+set+"='"+value+"' order by no desc;";
		selectQuery(sql);

		ArrayList<List> tmp = new ArrayList<List>();
		while (rs.next()) {
			List listTmp = new List();
			listTmp.setContent(rs.getString("CONTENT"));
			listTmp.setDate(rs.getString("DATE"));
			listTmp.setNo(rs.getInt("NO"));
			listTmp.setTitle(rs.getString("TITLE"));
			listTmp.setWriter(rs.getString("WRITER"));
			tmp.add(listTmp);
		}
		return tmp;
	}
	// �Խ��� �������������� �˻��� �� ��� �ش�����(set)�� �´� ��(value)�� select�Ͽ� ArrayList�� �߰���Ų��.
}