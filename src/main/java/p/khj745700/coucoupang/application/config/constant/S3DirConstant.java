package p.khj745700.coucoupang.application.config.constant;

public enum S3DirConstant {
    PRODUCT("product"),
    PRODUCT_DESCRIPTION("product-description");

    private final String baseDir;

    S3DirConstant(String dir) {
        baseDir = dir;
    }

    public String getBaseDir() {
        return baseDir;
    }
}
