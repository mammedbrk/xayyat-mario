package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.model.User;

import java.util.List;

public class RankingListListener {
    private final UserController controller = new UserController();

    public List<User> listen() {
        return controller.getSortedUsersList();
    }
}
