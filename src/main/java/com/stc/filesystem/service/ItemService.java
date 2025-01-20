package com.stc.filesystem.service;

import com.stc.filesystem.enums.ItemType;
import com.stc.filesystem.enums.PermissionLevel;
import com.stc.filesystem.exception.ParentItemNotFoundException;
import com.stc.filesystem.exception.UnauthorizedUserException;
import com.stc.filesystem.model.entity.File;
import com.stc.filesystem.model.entity.Item;
import com.stc.filesystem.model.entity.Permission;
import com.stc.filesystem.model.entity.PermissionGroup;
import com.stc.filesystem.repository.FileRepository;
import com.stc.filesystem.repository.ItemRepository;
import com.stc.filesystem.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements ItemServiceInterface{

    private final PermissionServiceInterface permissionService;
    private final ItemRepository itemRepository;
    private final PermissionRepository permissionRepository;
    private final FileRepository fileRepository;

    public ItemService(PermissionServiceInterface permissionService, ItemRepository itemRepository, PermissionRepository permissionRepository, FileRepository fileRepository) {
        this.permissionService = permissionService;
        this.itemRepository = itemRepository;
        this.permissionRepository = permissionRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Item createSpaceItem(String name) {
        PermissionGroup adminPermissionGroup = permissionService.createAdminPermissionGroup();
        Item spaceItem = Item.builder()
                .type(ItemType.Space)
                .name(name)
                .permissionGroup(adminPermissionGroup)
                .build();
        itemRepository.save(spaceItem);
        return itemRepository.save(spaceItem);
    }

    @Override
    public Item createFolderItemInSpace(String name, String parentSpaceName, String userEmail) {

        return createChildItem(name, parentSpaceName, ItemType.Folder, userEmail);
    }

    @Override
    public Item createFileItemInFolder(String name, String parentFolderName, String userEmail) {

        Item createdFileItem = createChildItem(name, parentFolderName, ItemType.File, userEmail);

        //create File
        byte[] file = "assessment content".getBytes();
        fileRepository.save(File.builder()
                .item(createdFileItem)
                .binaryData(file)
                .build());

         return createdFileItem;
    }

    private Item createChildItem(String itemName, String parentName, ItemType itemType, String userEmail) {
        Item parentItem = itemRepository.findByName(parentName);
        if (parentItem == null) {
            throw new ParentItemNotFoundException();
        }
        if(!validateEditUser(userEmail, parentItem)) {
            throw new UnauthorizedUserException();
        }
        Item createdFolder = itemRepository.save(Item.builder()
                .type(itemType)
                .name(itemName)
                .permissionGroup(parentItem.getPermissionGroup())
                .build());
        return itemRepository.save(createdFolder);
    }

    private Boolean validateEditUser(String userEmail, Item parentItem) {

        List<Permission> permissions = permissionRepository.findByUserEmailAndPermissionLevelAndGroupId(userEmail, PermissionLevel.EDIT.name(), parentItem.getPermissionGroup().getId());
        if (permissions != null && !permissions.isEmpty()) {
            return true;
        }

        return false;
    }

}
