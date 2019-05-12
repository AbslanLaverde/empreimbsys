package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Manager;
import com.revature.beans.PendingReq;
import com.revature.beans.Reimbursement;
import com.revature.beans.SubmissionReq;
import com.revature.beans.Updater;
import com.revature.services.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.revature.util.ConnectionUtil;
import com.revature.util.HttpException;

public class RequestDao {
	
	public HashSet viewRequestsByUserId(int id) {
		HashSet<Reimbursement> reimbSet = new HashSet<>();
		
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
				
				reimbSet.add(new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, description, reimbReceipt, reimbAuthor,
						reimbResolver, reimbStatus, reimbType));
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);			
			}
		return reimbSet;
	
	}
	
	
	
	public void sendRequestByUserId(SubmissionReq subReq) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_description, reimb_receipt, reimb_author, reimb_resolver," + 
					"reimb_status_id, reimb_type_id) values (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, subReq.getAmount());
			ps.setString(2, subReq.getDescription());
			ps.setString(3, subReq.getReimbReceipt());
			ps.setLong(4, LoginService.userId);
			ps.setLong(5, subReq.getManagerId());
			ps.setLong(6, 1);
			ps.setLong(7, subReq.getReimbType());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);
		}
	}



	public HashSet pullManagersFromUsers() {
		HashSet<Manager> managerList = new HashSet<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE user_role_id = 2";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("ers_user_id");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				
				managerList.add(new Manager(id, firstName, lastName));
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);			
			}

		
		return managerList;
	}



	public HashSet viewPendingRequests(int id) {
		HashSet<PendingReq> pendingList = new HashSet<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_description, reimb_receipt, reimb_author, reimb_type_id FROM ers_reimbursement WHERE reimb_resolver = ? and reimb_status_id = 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, LoginService.userId);
			System.out.println(LoginService.userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int reimbId = rs.getInt("reimb_id");
				double reimbAmount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				String description = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				int authorId = rs.getInt("reimb_author");
				int reimbType = rs.getInt("reimb_type_id");
				
				pendingList.add(new PendingReq(reimbId, reimbAmount, submitted, description, receipt, authorId,
						reimbType));
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);			
			}
		return pendingList;
	}



	public void updateRequest(Updater update) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement set reimb_status_id = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setLong(1, update.getReimbStatus());
			ps.setLong(2, update.getReimbId());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);
		}
	
		
	}
}
