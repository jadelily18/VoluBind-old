package com.lilydev.volubind.config;

import com.lilydev.volubind.Volubind;
import com.lilydev.volubind.VolubindClient;
import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    private static final String fileName = Volubind.MOD_ID + "-config.properties";


    public static boolean isMasterToggled;
    public static String isMasterToggledId = "is_master_toggled";
    public static int MASTER_VOL_NORMAL;
    public static int MASTER_VOL_TOGGLED;
    public static String MASTER_VOL_NORMAL_ID = "master_vol_normal";
    public static String MASTER_VOL_TOGGLED_ID = "master_vol_toggled";

    public static boolean isMusicToggled;
    public static String isMusicToggledId = "is_music_toggled";
    public static int MUSIC_VOL_NORMAL;
    public static int MUSIC_VOL_TOGGLED;
    public static String MUSIC_VOL_NORMAL_ID = "music_vol_normal";
    public static String MUSIC_VOL_TOGGLED_ID = "music_vol_toggled";

    public static boolean isRecordsToggled;
    public static String isRecordsToggledId = "is_records_toggled";
    public static int RECORDS_VOL_NORMAL;
    public static int RECORDS_VOL_TOGGLED;
    public static String RECORDS_VOL_NORMAL_ID = "records_vol_normal";
    public static String RECORDS_VOL_TOGGLED_ID = "records_vol_toggled";

    public static boolean isWeatherToggled;
    public static String isWeatherToggledId = "is_weather_toggled";
    public static int WEATHER_VOL_NORMAL;
    public static int WEATHER_VOL_TOGGLED;
    public static String WEATHER_VOL_NORMAL_ID = "weather_vol_normal";
    public static String WEATHER_VOL_TOGGLED_ID = "weather_vol_toggled";

    public static boolean isBlockToggled;
    public static String isBlockToggledId = "is_block_toggled";
    public static int BLOCK_VOL_NORMAL;
    public static int BLOCK_VOL_TOGGLED;
    public static String BLOCK_VOL_NORMAL_ID = "block_vol_normal";
    public static String BLOCK_VOL_TOGGLED_ID = "block_vol_toggled";

    public static boolean isHostileToggled;
    public static String isHostileToggledId = "is_hostile_toggled";
    public static int HOSTILE_VOL_NORMAL;
    public static int HOSTILE_VOL_TOGGLED;
    public static String HOSTILE_VOL_NORMAL_ID = "hostile_vol_normal";
    public static String HOSTILE_VOL_TOGGLED_ID = "hostile_vol_toggled";

    public static boolean isNeutralToggled;
    public static String isNeutralToggledId = "is_neutral_toggled";
    public static int NEUTRAL_VOL_NORMAL;
    public static int NEUTRAL_VOL_TOGGLED;
    public static String NEUTRAL_VOL_NORMAL_ID = "neutral_vol_normal";
    public static String NEUTRAL_VOL_TOGGLED_ID = "neutral_vol_toggled";

    public static boolean isPlayersToggled;
    public static String isPlayersToggledId = "is_players_toggled";
    public static int PLAYERS_VOL_NORMAL;
    public static int PLAYERS_VOL_TOGGLED;
    public static String PLAYERS_VOL_NORMAL_ID = "players_vol_normal";
    public static String PLAYERS_VOL_TOGGLED_ID = "players_vol_toggled";

    public static boolean isAmbientToggled;
    public static String isAmbientToggledId = "is_ambient_toggled";
    public static int AMBIENT_VOL_NORMAL;
    public static int AMBIENT_VOL_TOGGLED;
    public static String AMBIENT_VOL_NORMAL_ID = "ambient_vol_normal";
    public static String AMBIENT_VOL_TOGGLED_ID = "ambient_vol_toggled";

    public static boolean isVoiceToggled;
    public static String isVoiceToggledId = "is_voice_toggled";
    public static int VOICE_VOL_NORMAL;
    public static int VOICE_VOL_TOGGLED;
    public static String VOICE_VOL_NORMAL_ID = "voice_vol_normal";
    public static String VOICE_VOL_TOGGLED_ID = "voice_vol_toggled";

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Volubind.MOD_ID + "-config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>(isMasterToggledId, false));
        configs.addKeyValuePair(new Pair<>("master_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("master_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isMusicToggledId, false));
        configs.addKeyValuePair(new Pair<>("music_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("music_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isRecordsToggledId, false));
        configs.addKeyValuePair(new Pair<>("records_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("records_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isWeatherToggledId, false));
        configs.addKeyValuePair(new Pair<>("weather_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("weather_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isBlockToggledId, false));
        configs.addKeyValuePair(new Pair<>("block_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("block_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isHostileToggledId, false));
        configs.addKeyValuePair(new Pair<>("hostile_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("hostile_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isNeutralToggledId, false));
        configs.addKeyValuePair(new Pair<>("neutral_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("neutral_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isPlayersToggledId, false));
        configs.addKeyValuePair(new Pair<>("players_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("players_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isAmbientToggledId, false));
        configs.addKeyValuePair(new Pair<>("ambient_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("ambient_vol_toggled", 0));

        configs.addKeyValuePair(new Pair<>(isVoiceToggledId, false));
        configs.addKeyValuePair(new Pair<>("voice_vol_normal", 100));
        configs.addKeyValuePair(new Pair<>("voice_vol_toggled", 0));
    }

    public static void editConfigs(Pair<String, String> keyValuePair) {
        CONFIG.set(fileName, keyValuePair.getFirst(), keyValuePair.getSecond());
    }

    public static void saveConfigs() {
        editConfigs(new Pair<>(isMasterToggledId, String.valueOf(isMasterToggled)));
        editConfigs(new Pair<>(MASTER_VOL_NORMAL_ID, String.valueOf(MASTER_VOL_NORMAL)));
        editConfigs(new Pair<>(MASTER_VOL_TOGGLED_ID, String.valueOf(MASTER_VOL_TOGGLED)));

        editConfigs(new Pair<>(isMusicToggledId, String.valueOf(isMusicToggled)));
        editConfigs(new Pair<>(MUSIC_VOL_NORMAL_ID, String.valueOf(MUSIC_VOL_NORMAL)));
        editConfigs(new Pair<>(MUSIC_VOL_TOGGLED_ID, String.valueOf(MUSIC_VOL_TOGGLED)));

        editConfigs(new Pair<>(isRecordsToggledId, String.valueOf(isRecordsToggled)));
        editConfigs(new Pair<>(RECORDS_VOL_NORMAL_ID, String.valueOf(RECORDS_VOL_NORMAL)));
        editConfigs(new Pair<>(RECORDS_VOL_TOGGLED_ID, String.valueOf(RECORDS_VOL_TOGGLED)));

        editConfigs(new Pair<>(isWeatherToggledId, String.valueOf(isWeatherToggled)));
        editConfigs(new Pair<>(WEATHER_VOL_NORMAL_ID, String.valueOf(WEATHER_VOL_NORMAL)));
        editConfigs(new Pair<>(WEATHER_VOL_TOGGLED_ID, String.valueOf(WEATHER_VOL_TOGGLED)));

        editConfigs(new Pair<>(isBlockToggledId, String.valueOf(isBlockToggled)));
        editConfigs(new Pair<>(BLOCK_VOL_NORMAL_ID, String.valueOf(BLOCK_VOL_NORMAL)));
        editConfigs(new Pair<>(BLOCK_VOL_TOGGLED_ID, String.valueOf(BLOCK_VOL_TOGGLED)));

        editConfigs(new Pair<>(isHostileToggledId, String.valueOf(isHostileToggled)));
        editConfigs(new Pair<>(HOSTILE_VOL_NORMAL_ID, String.valueOf(HOSTILE_VOL_NORMAL)));
        editConfigs(new Pair<>(HOSTILE_VOL_TOGGLED_ID, String.valueOf(HOSTILE_VOL_TOGGLED)));

        editConfigs(new Pair<>(isNeutralToggledId, String.valueOf(isNeutralToggled)));
        editConfigs(new Pair<>(NEUTRAL_VOL_NORMAL_ID, String.valueOf(NEUTRAL_VOL_NORMAL)));
        editConfigs(new Pair<>(NEUTRAL_VOL_TOGGLED_ID, String.valueOf(NEUTRAL_VOL_TOGGLED)));

        editConfigs(new Pair<>(isPlayersToggledId, String.valueOf(isPlayersToggled)));
        editConfigs(new Pair<>(PLAYERS_VOL_NORMAL_ID, String.valueOf(PLAYERS_VOL_NORMAL)));
        editConfigs(new Pair<>(PLAYERS_VOL_TOGGLED_ID, String.valueOf(PLAYERS_VOL_TOGGLED)));

        editConfigs(new Pair<>(isAmbientToggledId, String.valueOf(isAmbientToggled)));
        editConfigs(new Pair<>(AMBIENT_VOL_NORMAL_ID, String.valueOf(AMBIENT_VOL_NORMAL)));
        editConfigs(new Pair<>(AMBIENT_VOL_TOGGLED_ID, String.valueOf(AMBIENT_VOL_TOGGLED)));

        editConfigs(new Pair<>(isVoiceToggledId, String.valueOf(isVoiceToggled)));
        editConfigs(new Pair<>(VOICE_VOL_NORMAL_ID, String.valueOf(VOICE_VOL_NORMAL)));
        editConfigs(new Pair<>(VOICE_VOL_TOGGLED_ID, String.valueOf(VOICE_VOL_TOGGLED)));

        Volubind.LOGGER.info(Volubind.MOD_NAME + " config saved!");
    }

    private static void assignConfigs() {
        isMasterToggled = CONFIG.getOrDefault(isMasterToggledId, false);
        MASTER_VOL_NORMAL = CONFIG.getOrDefault("master_vol_normal", 100);
        MASTER_VOL_TOGGLED = CONFIG.getOrDefault("master_vol_toggled", 0);

        isMusicToggled = CONFIG.getOrDefault(isMusicToggledId, false);
        MUSIC_VOL_NORMAL = CONFIG.getOrDefault("music_vol_normal", 100);
        MUSIC_VOL_TOGGLED = CONFIG.getOrDefault("music_vol_toggled", 0);

        isRecordsToggled = CONFIG.getOrDefault(isRecordsToggledId, false);
        RECORDS_VOL_NORMAL = CONFIG.getOrDefault("records_vol_normal", 100);
        RECORDS_VOL_TOGGLED = CONFIG.getOrDefault("records_vol_toggled", 0);

        isWeatherToggled = CONFIG.getOrDefault(isWeatherToggledId, false);
        WEATHER_VOL_NORMAL = CONFIG.getOrDefault("weather_vol_normal", 100);
        WEATHER_VOL_TOGGLED = CONFIG.getOrDefault("weather_vol_toggled", 0);

        isBlockToggled = CONFIG.getOrDefault(isBlockToggledId, false);
        BLOCK_VOL_NORMAL = CONFIG.getOrDefault("block_vol_normal", 100);
        BLOCK_VOL_TOGGLED = CONFIG.getOrDefault("block_vol_toggled", 0);

        isHostileToggled = CONFIG.getOrDefault(isHostileToggledId, false);
        HOSTILE_VOL_NORMAL = CONFIG.getOrDefault("hostile_vol_normal", 100);
        HOSTILE_VOL_TOGGLED = CONFIG.getOrDefault("hostile_vol_toggled", 0);

        isNeutralToggled = CONFIG.getOrDefault(isNeutralToggledId, false);
        NEUTRAL_VOL_NORMAL = CONFIG.getOrDefault("neutral_vol_normal", 100);
        NEUTRAL_VOL_TOGGLED = CONFIG.getOrDefault("neutral_vol_toggled", 0);

        isPlayersToggled = CONFIG.getOrDefault(isPlayersToggledId, false);
        PLAYERS_VOL_NORMAL = CONFIG.getOrDefault("players_vol_normal", 100);
        PLAYERS_VOL_TOGGLED = CONFIG.getOrDefault("players_vol_toggled", 0);

        isAmbientToggled = CONFIG.getOrDefault(isAmbientToggledId, false);
        AMBIENT_VOL_NORMAL = CONFIG.getOrDefault("ambient_vol_normal", 100);
        AMBIENT_VOL_TOGGLED = CONFIG.getOrDefault("ambient_vol_toggled", 0);

        isVoiceToggled = CONFIG.getOrDefault(isVoiceToggledId, false);
        VOICE_VOL_NORMAL = CONFIG.getOrDefault("voice_vol_normal", 100);
        VOICE_VOL_TOGGLED = CONFIG.getOrDefault("voice_vol_toggled", 0);
        
        SimpleConfig.LOGGER.info("All " + configs.getConfigsList().size() + " variables have been set properly!");
    }
}