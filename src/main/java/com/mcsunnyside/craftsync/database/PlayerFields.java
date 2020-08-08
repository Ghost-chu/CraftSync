package com.mcsunnyside.craftsync.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class PlayerFields {
    private UUID serial;
    private Map<String, String> fields;
}
