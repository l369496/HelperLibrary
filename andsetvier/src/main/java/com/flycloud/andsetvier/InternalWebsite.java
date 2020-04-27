package com.flycloud.andsetvier;

import com.yanzhenjie.andserver.framework.website.StorageWebsite;

public class InternalWebsite extends StorageWebsite {

    public InternalWebsite() {
        super(PathManager.getWebDir(),PathManager.getRelativeWebMainIndex());
    }
}
