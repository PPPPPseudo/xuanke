package com.neusoft.stucou.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stucou.dao.TeacherDAO;
import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.po.Teacher;
import com.neusoft.stucou.util.BaseDAO;

public class TeacherDAOimpl extends BaseDAO implements TeacherDAO{

	@Override
	public List<Teacher> showTeacher(String teachername) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select * from teacher where 1=1");
		 if(teachername!=null&&!teachername.equals("")) {
	            sql.append(" and teachername like '%"+teachername+"%' ");
	        }
	        PreparedStatement pstmt = null;
			ResultSet rs = null;
			String  sq=sql.toString();
			List <Teacher> tlist = new ArrayList<Teacher>();
			try {
		          pstmt =getConn().prepareStatement(sq);
		          rs=pstmt.executeQuery();
		          while (rs.next()){
		        	  Teacher t = new Teacher();
		        	  t.setTeacherid(rs.getInt("teacherid"));
		        	  t.setTeachername(rs.getString("teachername"));
		        	  t.setTechnology(rs.getString("technology"));
		        	  tlist.add(t);
		          }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {	
		            close(null,pstmt,rs);
		        }
		return tlist;
	}

	@Override
	public int saveTeacher(String teachername, String technology) {
		// TODO Auto-generated method stub
		String sql ="insert into teacher (teachername,technology) values(?,?)";
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt =getConn().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, teachername);
			pstmt.setString(2, technology);

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
	public Teacher getTeacherid(int teacherid) {
		// TODO Auto-generated method stub
		String sql = "select * from teacher where teacherid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher c=null;
        try {
          pstmt =getConn().prepareStatement(sql);
          pstmt.setInt(1, teacherid);
          rs=pstmt.executeQuery();
          if (rs.next()){
        	  c = new Teacher();
        	  c.setTeacherid(rs.getInt("teacherid"));
        	  c.setTeachername(rs.getString("teachername"));
        	  c.setTechnology(rs.getString("technology"));
          }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null,pstmt,rs);
        }
        return c;
	}

	@Override
	public int updateTeacher(Teacher t) {
		// TODO Auto-generated method stub
		String sql = "update teacher set teachername =?,"+
				"technology=? where teacherid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num=0;
		try {
			pstmt =getConn().prepareStatement(sql);
			pstmt.setInt(3, t.getTeacherid());
		    pstmt.setString(1, t.getTeachername());
		    pstmt.setString(2, t.getTechnology());
		    num=pstmt.executeUpdate();
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    	} finally {
		    		close(null,pstmt,rs);
		    		}
		return num;
	}

	@Override
	public int removeTeacher(int teacherId) {
		// TODO Auto-generated method stub
		String sql1 ="delete from teacher where teacherid =?";
		String sql2 ="delete from selection where courseid =(select courseid from course where teacherid=?)";
		String sql3 ="delete from course where teacherid =?";
		PreparedStatement pstmt = null;
		int num=0;
		try {
			getConn().setAutoCommit(false);
			pstmt =getConn().prepareStatement(sql1);
			pstmt.setInt(1, teacherId);
			num=pstmt.executeUpdate();
			
			pstmt =getConn().prepareStatement(sql2);
			pstmt.setInt(1, teacherId);
			pstmt.executeUpdate();
			
			pstmt =getConn().prepareStatement(sql3);
			pstmt.setInt(1, teacherId);
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
	public List<Student> showstu(int teacherid) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT stu.* FROM student AS stu,course AS c,selection AS s"
				+ " WHERE stu.`studentid` IN(SELECT studentid FROM selection WHERE "
				+ "courseid=ANY (SELECT courseid FROM course WHERE teacherid = ?))";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List <Student> slist = new ArrayList<Student>();
		try {
			pstmt =getConn().prepareStatement(sql);
			pstmt.setInt(1, teacherid);
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
