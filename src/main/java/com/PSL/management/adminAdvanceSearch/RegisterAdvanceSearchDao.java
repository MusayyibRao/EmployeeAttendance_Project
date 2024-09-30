package com.PSL.management.adminAdvanceSearch;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PSL.management.dataModel.AdminEntity;

@Repository
public interface RegisterAdvanceSearchDao {

	List<AdminEntity> searchAdminData(Long id, String firstName, String lastName, String username, String createdBy,
									  String modifyBy);

}
