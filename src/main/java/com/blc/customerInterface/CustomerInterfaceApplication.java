package com.blc.customerInterface;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.service.CategoryService;
import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.service.ImageService;
import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import com.blc.customerInterface.graphql.permission.repo.PermissionRepo;
import com.blc.customerInterface.graphql.permission.service.PermissionService;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CustomerInterfaceApplication implements CommandLineRunner {
	private final PermissionService permissionService;
	private final PermissionRepo permissionRepo;
	private final RoleService roleService;
	private final CategoryService categoryService;
	private final ImageService imageService;
	private final FlavorService flavorService;


	@Autowired
	public CustomerInterfaceApplication(PermissionService permissionService, PermissionRepo permissionRepo, RoleService roleService, CategoryService categoryService, ImageService imageService, FlavorService flavorService) {
		this.permissionService = permissionService;
		this.permissionRepo = permissionRepo;
		this.roleService = roleService;
		this.categoryService = categoryService;
		this.imageService = imageService;
		this.flavorService = flavorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerInterfaceApplication.class, args);
	}

	public void createDefaultCategory(){
		Category categoryHadoop = new Category();
		categoryHadoop.setName("HADOOP");
		Category categoryKubernetes = new Category();
		categoryKubernetes.setName("KUBERNETES");
		Category categoryDocker = new Category();
		categoryDocker.setName("DOCKER");
		Category categoryPostgresql = new Category();
		categoryPostgresql.setName("POSTGRESQL");
		Category categoryMongodb = new Category();
		categoryMongodb.setName("MONGODB");
		categoryService.saveAll(List.of(categoryHadoop,categoryKubernetes,categoryDocker,categoryPostgresql,categoryMongodb));

	}

	public void createDefaultImage(){
		Image imageUbuntu18 = new Image();
		imageUbuntu18.setName("UBUNTU 18.04");
		Image imageUbuntu20 = new Image();
		imageUbuntu20.setName("UBUNTU 20.04");
		Image imageCentos = new Image();
		imageCentos.setName("CENTOS");
		imageService.saveAll(List.of(imageUbuntu18,imageUbuntu20,imageCentos));
	}

	public void createDefaultFlavor(){

		Flavor flavorSmall = new Flavor();
		flavorSmall.setName("m1.small");
		flavorSmall.setCpu_size(1);
		flavorSmall.setRam_size(2);
		flavorSmall.setRoot_disk(20);

		Flavor flavorMedium = new Flavor();
		flavorMedium.setName("m1.medium");
		flavorMedium.setCpu_size(2);
		flavorMedium.setRam_size(4);
		flavorMedium.setRoot_disk(40);

		Flavor flavorLarge = new Flavor();
		flavorLarge.setName("m1.large");
		flavorLarge.setCpu_size(4);
		flavorLarge.setRam_size(8);
		flavorLarge.setRoot_disk(80);

		Flavor flavorXlarge = new Flavor();
		flavorXlarge.setName("m1.xlarge");
		flavorXlarge.setCpu_size(8);
		flavorXlarge.setRam_size(16);
		flavorXlarge.setRoot_disk(160);

		flavorService.saveAll(List.of(flavorSmall,flavorMedium,flavorLarge,flavorXlarge));


	}
	public void createDefaultPermission(){
		Permission user_create = new Permission();
		user_create.setName(PermissionName.USER_CREATE);
		Permission user_update = new Permission();
		user_update.setName(PermissionName.USER_UPDATE);
		Permission user_delete = new Permission();
		user_delete.setName(PermissionName.USER_DELETE);
		Permission user_undelete = new Permission();
		user_undelete.setName(PermissionName.USER_UNDELETE);

		Permission instance_create = new Permission();
		instance_create.setName(PermissionName.INSTANCE_CREATE);
		Permission instance_update = new Permission();
		instance_update.setName(PermissionName.INSTANCE_UPDATE);
		Permission instance_delete = new Permission();
		instance_delete.setName(PermissionName.INSTANCE_DELETE);
		Permission instance_undelete = new Permission();
		instance_undelete.setName(PermissionName.INSTANCE_UNDELETE);

		Permission flavor_create = new Permission();
		flavor_create.setName(PermissionName.FLAVOR_CREATE);
		Permission flavor_update = new Permission();
		flavor_update.setName(PermissionName.FLAVOR_UPDATE);
		Permission flavor_delete = new Permission();
		flavor_delete.setName(PermissionName.FLAVOR_DELETE);
		Permission flavor_undelete = new Permission();
		flavor_undelete.setName(PermissionName.FLAVOR_UNDELETE);

		Permission category_create = new Permission();
		category_create.setName(PermissionName.CATEGORY_CREATE);
		Permission category_update = new Permission();
		category_update.setName(PermissionName.CATEGORY_UPDATE);
		Permission category_delete = new Permission();
		category_delete.setName(PermissionName.CATEGORY_DELETE);
		Permission category_undelete = new Permission();
		category_undelete.setName(PermissionName.CATEGORY_UNDELETE);

		Permission image_create = new Permission();
		image_create.setName(PermissionName.IMAGE_CREATE);
		Permission image_update = new Permission();
		image_update.setName(PermissionName.IMAGE_UPDATE);
		Permission image_delete = new Permission();
		image_delete.setName(PermissionName.IMAGE_DELETE);
		Permission image_undelete = new Permission();
		image_undelete.setName(PermissionName.IMAGE_UNDELETE);

		Permission pem_create = new Permission();
		pem_create.setName(PermissionName.PEM_CREATE);
		Permission pem_update = new Permission();
		pem_update.setName(PermissionName.PEM_UPDATE);
		Permission pem_delete = new Permission();
		pem_delete.setName(PermissionName.PEM_DELETE);
		Permission pem_undelete = new Permission();
		pem_undelete.setName(PermissionName.PEM_UNDELETE);

		Permission role_create = new Permission();
		role_create.setName(PermissionName.ROLE_CREATE);
		Permission role_update = new Permission();
		role_update.setName(PermissionName.ROLE_UPDATE);
		Permission role_delete = new Permission();
		role_delete.setName(PermissionName.ROLE_DELETE);
		Permission role_undelete = new Permission();
		role_undelete.setName(PermissionName.ROLE_UNDELETE);

		permissionService.saveAll(List.of(
				user_create, user_update, user_delete, user_undelete,
				instance_create,instance_update,instance_delete,instance_undelete,
				flavor_create,flavor_update,flavor_delete,flavor_undelete,
				category_create,category_update,category_delete,category_undelete,
				image_create,image_update,image_delete,image_undelete,
				pem_create,pem_update,pem_delete,pem_undelete,
				role_create,role_update,role_delete,role_undelete
				));

	}
	public void createDefaultRole(){

		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");

		List<Permission> permissionsAdmin = new ArrayList<>();
		for (int i=0;i<permissionService.findAll().size();i++){
			permissionsAdmin.add(permissionService.findAll().get(i));
		}
		roleAdmin.setPermissions(permissionsAdmin);
		roleService.save(roleAdmin);



		Role roleUser = new Role();
		roleUser.setName("USER");

		Permission instance_create = permissionRepo.findByName(PermissionName.INSTANCE_CREATE);
		Permission instance_update = permissionRepo.findByName(PermissionName.INSTANCE_UPDATE);
		Permission instance_delete = permissionRepo.findByName(PermissionName.INSTANCE_DELETE);
		Permission instance_undelete = permissionRepo.findByName(PermissionName.INSTANCE_UNDELETE);

		List<Permission> permissionsUser = new ArrayList<>();
		permissionsUser.addAll(List.of(instance_create,instance_update,instance_delete,instance_undelete));
		roleUser.setPermissions(permissionsUser);
		roleService.save(roleUser);

	}

	@Override
	public void run(String... args) throws Exception {
		if (categoryService.findAll().size()==0){
			createDefaultCategory();
		}
		if (imageService.findAll().size()==0){
			createDefaultImage();
		}
		if (flavorService.findAll().size()==0){
			createDefaultFlavor();
		}
		if (permissionService.findAll().size()==0){
			createDefaultPermission();
		}
		if (roleService.findAll().size()==0){
			createDefaultRole();
		}


	}
}
