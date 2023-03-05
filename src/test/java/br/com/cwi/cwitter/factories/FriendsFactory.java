package br.com.cwi.cwitter.factories;

import br.com.cwi.cwitter.domain.Friend;
import br.com.cwi.cwitter.domain.Status;

public class FriendsFactory {

    public static Friend get() {
        Friend friend = new Friend();
        friend.setId(SimpleFactory.getRandomLong());
        friend.setStatus(Status.ACCEPTED);
        friend.setFriendId(SimpleFactory.getRandomLong());
        friend.setUserId(SimpleFactory.getRandomLong());
        return friend;
    }
}
