package site.itwill.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HpDAO extends JdbcDAO{
	
	private static HpDAO _dao;
	
	private HpDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao = new HpDAO();
	}
	
	public static HpDAO getDAO() {
		return _dao;
	}
	
	// 환자 정보 관련 메소드
	// 1. 환자 입력 : 번호, 이름, 전화번호, 주소, 담당의사코드
	
	public int insertPatient(HpDTO patient) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "INSERT INTO PATIENT VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, patient.getNo());
			pstmt.setString(2, patient.getName());
			pstmt.setString(3, patient.getPhone());
			pstmt.setString(4, patient.getAddress());
			pstmt.setString(5, patient.getBirth());
			pstmt.setInt(6, patient.getDocno());
			
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL ERROR : insertPa"+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}
	
	// 2. 환자 정보 변경
	public int updatePatient(HpDTO patient) {
		Connection con = null;
		PreparedStatement  pstmt = null;
		int rows = 0;
		
		try {
			con = getConnection();
			
			String sql = "UPDATE PATIENT SET NAME = ?, PHONE = ?, ADDRESS = ?,BIRTH = ?, DOCNO = ? WHERE NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, patient.getName());
			pstmt.setString(2, patient.getPhone());
			pstmt.setString(3, patient.getAddress());
			pstmt.setString(4, patient.getBirth());
			pstmt.setInt(5, patient.getDocno());
			pstmt.setInt(6, patient.getNo());
			
			rows = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			System.out.println("SQL ERROR : updatePa"+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}
	
	// 3. 환자 정보 삭제 (번호 기준)
	public int deletePatient(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		
		try {
			con = getConnection();
			
			String sql = "DELETE FROM PATIENT WHERE NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("SQL ERROR : deletePa"+e.getMessage());
		} finally {
			close(con, pstmt);
		} return rows;
	}
	
	// 4. 모든 환자 정보 검색
	public List<HpDTO> selectAllPatientList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HpDTO> hpList = new ArrayList<HpDTO>();
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM PATIENT";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HpDTO hp = new HpDTO();
				hp.setNo(rs.getInt("no"));
				hp.setName(rs.getString("name"));
				hp.setPhone(rs.getString("phone"));
				hp.setAddress(rs.getString("address"));
				hp.setBirth(rs.getString("birth"));
				hp.setDocno(rs.getInt("docno"));
				hpList.add(hp);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL ERROR : selectAllPa"+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		} return hpList;
				
	}
	
	// 5. 환자 번호을 통한 환자 정보 검색
	public HpDTO selectNoPatient(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HpDTO hp = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM PATIENT WHERE NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				hp = new HpDTO();
				hp.setNo(rs.getInt("no"));
				hp.setName(rs.getString("name"));
				hp.setPhone(rs.getString("phone"));
				hp.setAddress(rs.getString("address"));
				hp.setBirth(rs.getString("birth"));
				hp.setDocno(rs.getInt("docno"));
			}
		} catch (SQLException e) {
			System.out.println("SQL ERROR : selectNoPa"+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		} return hp;
	}
	
	public List<HpDTO> selectNamePatientList(String name) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<HpDTO> patientList=new ArrayList<HpDTO>();
		try {
			con=getConnection();
			
			//String sql="select * from student where name=? order by no";
			String sql="select * from PATIENT where name like '%'||?||'%' order by no";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				HpDTO hp=new HpDTO();
				hp.setNo(rs.getInt("no"));
				hp.setName(rs.getString("name"));
				hp.setPhone(rs.getString("phone"));
				hp.setAddress(rs.getString("address"));
				hp.setBirth(rs.getString("birth").substring(0, 10));
				hp.setDocno(rs.getInt("docno"));
				patientList.add(hp);
			} 
		} catch (SQLException e) {
			System.out.println("[에러]selectNameStudentList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return patientList;
	}

}
