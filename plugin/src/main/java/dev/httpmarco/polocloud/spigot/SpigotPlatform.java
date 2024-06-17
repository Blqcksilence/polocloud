/*
 * Copyright 2024 Mirco Lindenau | HttpMarco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.httpmarco.polocloud.spigot;

import dev.httpmarco.polocloud.RunningPlatform;
import dev.httpmarco.polocloud.runner.CloudInstance;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotPlatform extends JavaPlugin implements Listener {

    private final RunningPlatform runningPlatform = new RunningPlatform();

    @Override
    public void onEnable() {
        runningPlatform.changeToOnline();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void handle(PlayerLoginEvent event) {
        if (CloudInstance.instance().self().isFull() && !event.getPlayer().hasPermission("polocloud.connect.bypass.maxplayers")) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, "§cThis service is full!");
        }
    }
}
