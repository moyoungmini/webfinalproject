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
	private Connection con;// DB에 접근 하기 위한 객체
	private Statement stmt; // statement 객체
	private ResultSet rs;// 정보를 담는 객체 (Select의 경우 사용을 많이한다.)

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// 드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}

		try {
			String jdbcUrl = "jdbc:mysql://localhost:3306/inp?autoReconnect=true&useSSL=false";// 사용하는 데이터베이스명을 포함한 url
			String userId = "root";// 사용자계정
			String userPass = "root";// 사용자 패스워드

			con = DriverManager.getConnection(jdbcUrl, userId, userPass);// Connection 객체를 얻어냄
			stmt = con.createStatement();// Statement 객체를 얻어냄

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

	// Insert Update Select Delete 쿼리의 경우 sql문을 실행할 때마다 con.createStatement의 코드가
	// 중복되므로 함수로 따로 분리하였다.

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
			// ID/pW 일치의 경우 로그인 성공
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
	// User Table에서 해당 아이디가 존재하는지 순회를 한다.

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
	// User Table에서 해당 닉네임이 존재하는지 순회를 한다.

	public boolean signUP(String id, String pw1, String pw2, String name, String address) {
		if (!pw1.equals(pw2) || id.equals("") || pw1.equals("") || name.equals("") || address.equals("") || idOverlap(id) || nameOverlap(name)) {
			return false;
		} else {
			String sql = "insert into user values('" + id + "','" + pw1 + "','" + name + "','" + address + "');";
			insertQuery(sql);
			return true;
		}
	}
	// 회원가입시 User 테이블에 삽입
	public boolean update(String id, String pw1, String pw2, String name, String address) {
		if (!pw1.equals(pw2) || pw1.equals("") || name.equals("") || name.equals("") || address.equals("")) {
			return false;
		}
		// ID,PW,Name,Address가 하나라도 빌 경우 예외 처리를 한다.
		String sql = "update user set pw='" + pw1 + "', name='" + name + "', address='" + address + "' where id='" + id
				+ "';";
		updateQuery(sql);
		return true;
	}
	// change.jsp에서 회원정보 수정을 위해 업데이트를 할때 사용하는 쿼리문이다.

	public String getDate() throws SQLException {
		String formatType = "yyyy-MM-dd-hh-mm-ss";
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat(formatType);
		String nowTime = dayTime.format(new Date(time));
		return nowTime;
	}
	// 현재 시간을 부르는 함수이다.
	
	public int getNo() throws SQLException {
		String sql = "select no from bbs order by no desc limit 1;";
		selectQuery(sql);
		int no = 0;
		while (rs.next()) {
			no = Integer.parseInt(rs.getString("no"));
		}
		return no + 1;
	}
	// 게시판 글 작성 시 bbs 테이블에서 마지막 no값에 +1 값 리턴

	public boolean writeContent(int no, String title, String writer, String date, String content) throws SQLException {
		String sql = "select * from bbs where no='" + no + "';";
		selectQuery(sql);
		while (rs.next()) {
			sql = "update bbs set title='" + title + "', writer = '" + writer + "', date = '" + date + "', content='"
					+ content + "' where no='" + no + "';";
			updateQuery(sql);
			return true;
		} // 기존에 존재하는 게시판글일 경우 update 수행

		sql = "insert into bbs values('" + no + "','" + title + "','" + writer + "','" + date + "','" + content + "');";
		if (insertQuery(sql)) {
			return true;
		} else {
			return false;
		} // 새로운 글을 작성 할 경우 insert 수행
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
	// BBS Table에서 해당 값들을 읽어 온후 listTmp에 넣은 다음 listTmp를 tmp 추가하여 ArrayList를 만든다.
	// DB에 존재하는 모든 값들을 읽어온 후 ArrayList를 반환한다.

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
	// 해당 no의 같은 레코드를 갖는 열들의 값들을 가져와 리턴한다.

	public boolean removeList(int no) {
		String sql = "delete from bbs where no='" + no + "';";
		if (deleteQuery(sql)) {
			return true;
		} else {
			return false;
		}
	}
	// 게시판에서 해당 no를 삭제할때 사용한다.

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
	//관리자의 유저관리페이지에서 유저에 해당하는 리스트들을 뽑을때 사용한다.

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
	// 관리자가 user를 삭제할 때 사용한다.
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
	// 게시판 메인페이지에서 검색을 할 경우 해당조건(set)에 맞는 값(value)를 select하여 ArrayList에 추가시킨다.
}