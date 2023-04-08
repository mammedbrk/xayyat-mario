package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.event.ListEvent;
import com.mammedbrk.model.User;

import java.util.ArrayList;
import java.util.List;

public class RankingListListener implements Listener<ListEvent> {
    private final UserController controller = new UserController();
    @Override
    public boolean listen(ListEvent listEvent) {
        listEvent.getUsers().addAll(controller.getSortedUsersList());
        return true;
    }
}
