package com.lzw.core;

import com.alibaba.dubbo.config.annotation.Service;
import com.lzw.core.base.BaseProviderImpl;
import com.lzw.provider.ISysProvider;

@Service(interfaceClass = ISysProvider.class)
public class SysProviderImpl extends BaseProviderImpl implements ISysProvider {
	
}