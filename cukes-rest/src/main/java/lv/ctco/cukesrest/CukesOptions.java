package lv.ctco.cukesrest;

/**
 * This annotation provides list of variables and options for Cukes framework.
 */
public interface CukesOptions {
    String PROPERTIES_PREFIX_VAR = "cukes.var";
    String PROPERTIES_PREFIX = "cukes.";

    String RESOURCES_ROOT = "resources_root";
    String BASE_URI = "base_uri";
    String PROXY = "proxy";
    String LTPA_TOKEN_URL = "ltpa_token_url";
    String ENVIRONMENT = "environment";
    String AUTH_TYPE = "auth_type";
    String USERNAME = "username";
    String PASSWORD = "password";

    String URL_ENCODING_ENABLED = "url_encoding_enabled";
    String RELAXED_HTTPS = "relaxed_https";
    String CONTEXT_INFLATING_ENABLED = "context_inflating_enabled";
    String ASSERTIONS_DISABLED = "assertions_disabled";

    String LOADRUNNER_FILTER_BLOCKS_REQUESTS = "loadrunner_filter_blocks_requests";
}
