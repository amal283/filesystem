package com.stc.filesystem.service;

import com.stc.filesystem.model.entity.Item;

import java.io.IOException;

public interface ItemServiceInterface {

    Item createSpaceItem(String name);

    Item createFolderItemInSpace(String name, String parentSpaceName, String userEmail);

    Item createFileItemInFolder(String name, String parentFolderName, String userEmail) throws IOException;
}
