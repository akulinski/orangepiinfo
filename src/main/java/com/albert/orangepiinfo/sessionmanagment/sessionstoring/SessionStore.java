package com.albert.orangepiinfo.sessionmanagment.sessionstoring;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class SessionStore {

    @Getter
    private BiMap<String, ISavable> saveableHashMap;

    private SessionStore() {
        this.saveableHashMap = HashBiMap.create();
    }

    public void saveISaveable(String sessionId, ISavable iSavable) {
        this.saveableHashMap.put(sessionId, iSavable);
    }

    public void deleteISavableBySessionId(String sessionId) {
        this.saveableHashMap.remove(sessionId);
    }

    public void delateISaveableByISaveable(ISavable iSavable) {
        saveableHashMap.inverse().remove(iSavable);
    }

    public boolean sessionExists(String session) {
        return this.saveableHashMap.containsKey(session);
    }

}
