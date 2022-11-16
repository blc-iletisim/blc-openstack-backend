package com.blc.customerInterface;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import com.blc.customerInterface.graphql.permission.service.PermissionService;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CustomerInterfaceApplication implements CommandLineRunner {
	private final PermissionService permissionService;
	private final RoleService roleService;


	@Autowired
	public CustomerInterfaceApplication(PermissionService permissionService, RoleService roleService) {
		this.permissionService = permissionService;
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerInterfaceApplication.class, args);
	}

	public void createDefaultPermission(){
		Permission permission = new Permission();
		permission.setName(PermissionName.USER_CREATE);
		Permission permission2 = new Permission();
		permission2.setName(PermissionName.USER_UPDATE);
		Permission permission3 = new Permission();
		permission3.setName(PermissionName.USER_DELETE);
		Permission permission4 = new Permission();
		permission4.setName(PermissionName.USER_UNDELETE);
		permissionService.saveAll(List.of(permission, permission2, permission3, permission4));

	}
	public void createDefaultRole(){
		Role role = new Role();
		role.setName("ADMIN");
		Set<Permission> permissions = new HashSet<>();
		for (int i=0;i<permissionService.findAll().size();i++){
			permissions.add(permissionService.findAll().get(i));
		}
		role.setPermissions(permissions);
		roleService.save(role);
	}

	@Override
	public void run(String... args) throws Exception {
		if (permissionService.findAll().size()==0){
			createDefaultPermission();
		}

		if (roleService.findAll().size()==0){
			createDefaultRole();
		}

	}
}
