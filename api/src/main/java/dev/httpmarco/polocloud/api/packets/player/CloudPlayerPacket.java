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

package dev.httpmarco.polocloud.api.packets.player;

import dev.httpmarco.osgan.networking.Packet;
import dev.httpmarco.osgan.networking.codec.CodecBuffer;
import dev.httpmarco.polocloud.api.packets.CloudComponentPacketHelper;
import dev.httpmarco.polocloud.api.player.CloudPlayer;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class CloudPlayerPacket extends Packet {

    private CloudPlayer cloudPlayer;

    public CloudPlayerPacket(CloudPlayer cloudPlayer) {
        this.cloudPlayer = cloudPlayer;
    }

    @Override
    public void onRead(CodecBuffer codecBuffer) {
        this.cloudPlayer = CloudComponentPacketHelper.readPlayer(codecBuffer);
    }

    @Override
    public void onWrite(CodecBuffer codecBuffer) {
        CloudComponentPacketHelper.writePlayer(cloudPlayer, codecBuffer);
    }
}
