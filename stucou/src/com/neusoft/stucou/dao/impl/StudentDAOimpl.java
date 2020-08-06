package com.neusoft.stucou.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stucou.dao.StudentDAO;
import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.util.BaseDAO;

public class StudentDAOimpl extends BaseDAO implements StudentDAO{

	@Override
	public int saveStudent( String studentname, String studentclass) {
		// TODO Auto-generated method stub
		String sql ="insert into student (studentname,studentclass) values(?,?)";
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt =getConn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, studentname);
			pstmt.setString(2, studentclass);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
	        if (rs.next()){
	        	id= rs.getInt(1);
	        	}
	        }
		catch (SQLException e) {
	            e.printStackTrace();
	        } finally {	
	            close(null,pstmt,rs);
	        }
	        return id;
		
	}

	@Override
	public List<Course> seCourse(String coursename, int teacherid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select * from course where 1=1");
		 if(coursename!=null&&!coursename.equals("")) {
	            sql.append(" and coursename like '%"+coursename+"%' ");
	        }
	        if(teacherid!=0) {
	            sql.append(" and teacherid like '%"+teacherid+"%' ");
	        }
	        PreparedStatement pstmt = null;
			ResultSet rs = null;
			String  sq=sql.toString();
			List <Course> clist = new ArrayList<Course>();
			try {
		          pstmt =getConn().prepareStatement(sq);
		          rs=pstmt.executeQuery();
		          while (rs.next()){
		        	  Course c = new Course();
		        	  c.setCourseid(rs.getInt("courseid"));
		        	  c.setTeacherid(rs.getInt("teacherid"));
		        	  c.setCoursetime(rs.getInt("coursetime"));
		        	  c.setCoursename(rs.getString("coursename"));
		        	  clist.add(c);
		          }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {	
		            close(null,pstmt,rs);
		        }

		return clist;
	}

	@Override
	public List<Course> showlist(int studentid) {
			String sql = "select * from course where courseid in (select courseid from selection where studentid =?)";
		
	        PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List <Course> clist = new ArrayList<Course>();
			try {
		          pstmt =getConn().prepareStatement(sql);
		          pstmt.setInt(1,studentid);
		          rs=pstmt.executeQuery();
		          while (rs.next()){
		        	  Course c = new Course();
		        	  c.setCourseid(rs.getInt("courseid"));
		        	  c.setTeacherid(rs.getInt("teacherid"));
		        	  c.setCoursetime(rs.getInt("coursetime"));
		        	  c.setCoursename(rs.getString("coursename"));
		        	  clist.add(c);
		          }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {	
		            close(null,pstmt,rs);
		        }

		return clist;
	}

	@Override
	public int saveCourse(int studentid, int courseid) {
		String sql ="insert into selection (studentid,courseid) values(?,?)";
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt =getConn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, studentid);
			pstmt.setInt(2, courseid);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
	        if (rs.next()){
	        	id= rs.getInt(1);
	        	}
	        }
		catch (SQLException e) {
	            e.printStackTrace();
	        } finally {	
	            close(null,pstmt,rs);
	        }
	        return id;
		
	}

	@Override
	public List<Student> showstu(int courseId) {
		// TODO Auto-generated method stub
		String sql ="select *from student where studentid in (select studentid from selection where courseid=?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <Student> slist = new ArrayList<Student>();
		try {
	          pstmt =getConn().prepareStatement(sql);
	          pstmt.setInt(1,courseId);
	          rs=pstmt.executeQuery();
	          while (rs.next()){
	        	  Student s = new Student();
	        	  s.setStudentid(rs.getInt("studentid"));
	        	  s.setStudentname(rs.getString("studentname"));
	        	  s.setStudentclass(rs.getString("studentclass"));
	        	  slist.add(s);
	          }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {	
	            close(null,pstmt,rs);
	        }

		return slist;
	}

}
