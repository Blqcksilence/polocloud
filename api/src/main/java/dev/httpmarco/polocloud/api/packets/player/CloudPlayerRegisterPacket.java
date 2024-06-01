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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Accessors(fluent = true)
public class CloudPlayerRegisterPacket extends Packet {

    private UUID id;
    private String name;

    public CloudPlayerRegisterPacket(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void onRead(CodecBuffer codecBuffer) {
        this.id = codecBuffer.readUniqueId();
        this.name = codecBuffer.readString();
    }

    @Override
    public void onWrite(CodecBuffer codecBuffer) {
        codecBuffer.writeUniqueId(this.id);
        codecBuffer.writeString(this.name);
    }
}
