package pl.crejk.haspaid.util;

import com.google.common.base.Charsets;

import java.util.UUID;

public final class UUIDUtil {

    private UUIDUtil() {
    }

    public static UUID fromIdWithoutBreak(String id) {
        final String uuid = id.substring(0, 8) + "-" +
                id.substring(8, 12) + "-" +
                id.substring(12, 16) + "-" +
                id.substring(16, 20) + "-" +
                id.substring(20, 32);

        return UUID.fromString(uuid);
    }

    public static UUID getOfflineId(String name) {
        final String offlinePlayer = "OfflinePlayer:" + name;
        final byte[] bytes = offlinePlayer.getBytes(Charsets.UTF_8);

        return UUID.nameUUIDFromBytes(bytes);
    }
}
