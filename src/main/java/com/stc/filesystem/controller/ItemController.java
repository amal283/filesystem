package com.stc.filesystem.controller;

import com.stc.filesystem.exception.ParentItemNotFoundException;
import com.stc.filesystem.exception.UnauthorizedUserException;
import com.stc.filesystem.model.entity.Item;
import com.stc.filesystem.model.request.User;
import com.stc.filesystem.service.ItemServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ItemController {

    private final ItemServiceInterface itemService;
    private final static String SPACE_NAME = "stc-assessments";
    private final static String FOLDER_NAME = "Backend";
    private final static String FILE_NAME = "assessment.pdf";
    ItemController(ItemServiceInterface itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create-stc-space")
    ResponseEntity<Item> createStcAssessmentsSpace() {

        Item item = itemService.createSpaceItem(SPACE_NAME);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/create-backend-folder")
    ResponseEntity<Item> createBackendFolder(@RequestBody User user) {
        Item item = null;
        try{

            item = itemService.createFolderItemInSpace(FOLDER_NAME, SPACE_NAME, user.getUserEmail());
        } catch (UnauthorizedUserException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (ParentItemNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/create-assessment-file")
    ResponseEntity<Item> createAssessmentFile(@RequestBody User user) throws IOException {
        Item item = null;
        try{

            item = itemService.createFileItemInFolder(FILE_NAME, FOLDER_NAME, user.getUserEmail());
        } catch (UnauthorizedUserException e) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (ParentItemNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
}
