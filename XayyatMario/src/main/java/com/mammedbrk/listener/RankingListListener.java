package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.event.ListEvent;

public class RankingListListener {
    private final UserController controller = new UserController();

    public boolean listen(ListEvent listEvent) { // todo return List
        listEvent.getUsers().addAll(controller.getSortedUsersList());
        return true;
    }
}
