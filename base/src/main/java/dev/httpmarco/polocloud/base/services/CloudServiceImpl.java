package dev.httpmarco.polocloud.base.services;

import dev.httpmarco.polocloud.api.groups.CloudGroup;
import dev.httpmarco.polocloud.api.properties.PropertiesPool;
import dev.httpmarco.polocloud.api.services.CloudService;
import dev.httpmarco.polocloud.api.services.ServiceState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class CloudServiceImpl implements CloudService {

    private CloudGroup group;
    private int orderedId;
    private UUID id;

    private int port;
    private String hostname;

    @Setter(AccessLevel.PACKAGE)
    private ServiceState state;

    private final PropertiesPool<?> properties = new PropertiesPool<>();
    private final Set<String> subscribedEvents = new HashSet<>();

    @Override
    public List<String> log() {
        //todo
        return List.of();
    }

    @Override
    public boolean isFull() {
        // todo
        return false;
    }

    @Override
    public int currentMemory() {
        // todo
        return 0;
    }

    @Override
    public int maxMemory() {
        // todo
        return 0;
    }

    @Override
    public int maxPlayers() {
        // todo
        return 0;
    }

    @Override
    public int onlinePlayers() {
        // todo with external nodes, local nodes method is overrited
        return 0;
    }

    @Override
    public String toString() {
        return "group=" + group +
                ", orderedId=" + orderedId +
                ", state=" + state +
                ", id=" + id;
    }
}
