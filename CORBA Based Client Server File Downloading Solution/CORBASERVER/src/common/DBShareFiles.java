	
//


package common;

import static common.DBConfiguration.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBShareFiles {

    int totalPage;

    public DBShareFiles() {

    }

    public void createTable() {
        try {
            String query = "create table if not exists sharefiles ( 	ClientId VARCHAR(30),	IpAddress VARCHAR(150),	PortNumber INT,	ClientName VARCHAR(200),	FileId INT AUTO_INCREMENT,	FileName VARCHAR(300),	FileLocation VARCHAR(450),	SharedDate DATE,	FileImage VARCHAR(900), PRIMARY KEY(FileId) )";
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteObject(Object o) {
        try {
            ShareFiles p = (ShareFiles) o;
            String query = "delete from sharefiles where FileName=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, p.getFileName());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ShareFiles findindDatabase(ShareFiles p) {
        try {
            String query = "select * from sharefiles where FileId = '" + p.getFileId() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                p.setClientId(rs.getString(1));
                p.setIpAddress(rs.getString(2));
                p.setPortNumber(rs.getInt(3));
                p.setClientName(rs.getString(4));
                p.setFileId(rs.getInt(5));
                p.setFileName(rs.getString(6));
                p.setFileLocation(rs.getString(7));
                p.setSharedDate(rs.getDate(8));
                p.setFileImage(rs.getString(9));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ShareFiles insertObject(ShareFiles p) {
        try {
            String query = "insert into sharefiles( ClientId,	IpAddress ,	PortNumber ,	ClientName ,	FileName ,	FileLocation ,	SharedDate ,	FileImage ) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, p.getClientId());
            st.setString(2, p.getIpAddress());
            st.setInt(3, p.getPortNumber());
            st.setString(4, p.getClientName());
            st.setString(5, p.getFileName());
            st.setString(6, p.getFileLocation());
            st.setDate(7, p.getSharedDate());
            st.setString(8, p.getFileImage());
            st.executeUpdate();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object rstoObject(ResultSet rs) {
        try {
            ShareFiles p = new ShareFiles();
            p.setClientId(rs.getString(1));
            p.setIpAddress(rs.getString(2));
            p.setPortNumber(rs.getInt(3));
            p.setClientName(rs.getString(4));
            p.setFileId(rs.getInt(5));
            p.setFileName(rs.getString(6));
            p.setFileLocation(rs.getString(7));
            p.setSharedDate(rs.getDate(8));
            p.setFileImage(rs.getString(9));
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateObject(Object o) {
        try {
            ShareFiles p = (ShareFiles) o;
            String query = "update  sharefiles set 	ClientId=?,IpAddress=? ,	PortNumber=? ,	ClientName=? ,	FileName=? ,	FileLocation=? ,	SharedDate=? ,	FileImage=? where FileId = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, p.getClientId());
            st.setString(2, p.getIpAddress());
            st.setInt(3, p.getPortNumber());
            st.setString(4, p.getClientName());
            st.setString(5, p.getFileName());
            st.setString(6, p.getFileLocation());
            st.setDate(7, p.getSharedDate());
            st.setString(8, p.getFileImage());
            st.setInt(9, p.getFileId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public javafx.collections.ObservableList<ShareFiles> loadGroupData(javafx.collections.ObservableList<ShareFiles> l, String whereQuery) {
        try {
            String query = "SELECT sharefiles.*"
                    + " FROM sharefiles ORDER BY (sharefiles.FileId)";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ShareFiles obj = (ShareFiles) rstoObject(rs);
                l.add(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public int getFilesCount(String whereQuery) {
        int totalFiles = 0;
        try {

            String query = "SELECT COUNT(sharefiles.FileId) FROM sharefiles ";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                totalFiles = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalFiles;
    }
    
    public int findLastInsertId() {
        try {
            String query = "select MAX(sharefiles.FileId)  from sharefiles";
            Statement st = DBConfiguration.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBShareFiles.class.getName()).log(Level.SEVERE, (String) null, ex);
            return -1;
        }
    }
}
