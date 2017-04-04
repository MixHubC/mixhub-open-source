package online.themixhub.demo.sql.impl;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobCommentMapper implements RowMapper<JobComment> {

    public JobComment mapRow(ResultSet rs, int rowNum) throws SQLException {
    	JobComment jobComment = new JobComment();
        jobComment.setId(rs.getInt("id"));
		jobComment.setDate(rs.getLong("date"));
		jobComment.setReply_to_id(rs.getInt("reply_to_id"));
		jobComment.setParent_account_id(rs.getInt("parent_account_id"));
		jobComment.setParent_job_id(rs.getInt("parent_job_id"));
		jobComment.setComment(rs.getString("comment"));
		jobComment.setFilepaths(rs.getString("filepaths"));
        return jobComment;
    }
}