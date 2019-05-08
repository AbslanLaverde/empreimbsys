package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Reimbursement;
import com.revature.services.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.revature.util.ConnectionUtil;
import com.revature.util.HttpException;

public class RequestDao {
	
	public ArrayList viewRequestsByUserId(int id) {
		ArrayList<Reimbursement> reimbList = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, LoginService.userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int reimbId = rs.getInt("reimb_id");
				double reimbAmount = rs.getDouble("reimb_amount");
				Timestamp reimbSubmitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimbResolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				String reimbReceipt = rs.getString("reimb_receipt");
				int reimbAuthor = rs.getInt("reimb_author");
				int reimbResolver = rs.getInt("reimb_resolver");
				int reimbStatus = rs.getInt("reimb_status_id");
				int reimbType = rs.getInt("reimb_type_id");
				
				reimbList.add(new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, description, reimbReceipt, reimbAuthor,
						reimbResolver, reimbStatus, reimbType));
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);			
			}
		return reimbList;
	
	}
	
	
	
	public void sendRequestByUserId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT"
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);
		}
	}

}
