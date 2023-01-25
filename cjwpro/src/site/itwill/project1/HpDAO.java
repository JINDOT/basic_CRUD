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
	
	// ȯ�� ���� ���� �޼ҵ�
	// 1. ȯ�� �Է� : ��ȣ, �̸�, ��ȭ��ȣ, �ּ�, ����ǻ��ڵ�
	
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
	
	// 2. ȯ�� ���� ����
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
	
	// 3. ȯ�� ���� ���� (��ȣ ����)
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
	
	// 4. ��� ȯ�� ���� �˻�
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
	
	// 5. ȯ�� ��ȣ�� ���� ȯ�� ���� �˻�
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
			System.out.println("[����]selectNameStudentList() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return patientList;
	}

}
