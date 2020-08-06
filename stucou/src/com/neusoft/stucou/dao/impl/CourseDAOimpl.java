package com.neusoft.stucou.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stucou.dao.CourseDAO;
import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.util.BaseDAO;

public class CourseDAOimpl extends BaseDAO implements CourseDAO {

	@Override
	public List<Course> showCourse(String coursename) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select * from course where 1=1");
		 if(coursename!=null&&!coursename.equals("")) {
	            sql.append(" and coursename like '%"+coursename+"%' ");
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
	public int saveCourse(int coursetime, int teacherid, String courseName) {
		// TODO Auto-generated method stub
		String sql ="insert into course (coursename,coursetime,teacherid) values(?,?,?)";
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt =getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, courseName);
			pstmt.setInt(2, coursetime);
			pstmt.setInt(3, teacherid);
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
	public int updateCourse(Course c) {
		// TODO Auto-generated method stub
		String sql = "update course set coursename =?,"+
				"coursetime=?,teacherid=? where courseid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num=0;
		try {
			pstmt =getConn().prepareStatement(sql);
			pstmt.setInt(4, c.getCourseid());
		    pstmt.setString(1, c.getCoursename());
		    pstmt.setInt(2, c.getCoursetime());
		    pstmt.setInt(3, c.getTeacherid());
		    num=pstmt.executeUpdate();
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    	} finally {
		    		close(null,pstmt,rs);
		    		}
		return num;
	}

	@Override
	public int removeCourse(int courseId) {
		// TODO Auto-generated method stub
		String sql1 ="delete from course where courseid =?";
		String sql2 ="delete from selection where courseid =?";
		PreparedStatement pstmt = null;
		int num=0;
		try {
			getConn().setAutoCommit(false);
			pstmt =getConn().prepareStatement(sql1);
			pstmt.setInt(1, courseId);
			num=pstmt.executeUpdate();
			
			pstmt =getConn().prepareStatement(sql2);
			pstmt.setInt(1, courseId);
			pstmt.executeUpdate();
			getConn().commit();
			}
		catch (SQLException e) {
			try {
				getConn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			} finally {
				close(null,pstmt,null);
				}
		return num;
	}

	@Override
	public Course getCourseid(int courseid) {
		// TODO Auto-generated method stub
		String sql = "select * from course where courseid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course c=null;
        try {
          pstmt =getConn().prepareStatement(sql);
          pstmt.setInt(1, courseid);
          rs=pstmt.executeQuery();
          if (rs.next()){
        	  c = new Course();
        	  c.setCourseid(rs.getInt("courseid"));
        	  c.setTeacherid(rs.getInt("teacherid"));
        	  c.setCoursename(rs.getString("coursename"));
        	  c.setCoursetime(rs.getInt("coursetime"));
          }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null,pstmt,rs);
        }
        return c;
	}

}
