/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.view;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

/**
 * Abstract base class for URL-based views. Provides a consistent way of
 * holding the URL that a View wraps, in the form of a "url" bean property.
 * 基于url的视图的抽象基类。提供一致的方法以“URL”bean属性的形式保存视图包装的URL
 * @author Juergen Hoeller
 * @since 13.12.2003
 */
public abstract class AbstractUrlBasedView extends AbstractView implements InitializingBean {

	@Nullable
	private String url;


	/**
	 * Constructor for use as a bean.
	 */
	protected AbstractUrlBasedView() {
	}

	/**
	 * Create a new AbstractUrlBasedView with the given URL.
	 * @param url the URL to forward to
	 */
	protected AbstractUrlBasedView(String url) {
		this.url = url;
	}


	/**
	 * Set the URL of the resource that this view wraps.
	 * The URL must be appropriate for the concrete View implementation.
	 */
	public void setUrl(@Nullable String url) {
		this.url = url;
	}

	/**
	 * Return the URL of the resource that this view wraps.
	 */
	@Nullable
	public String getUrl() {
		return this.url;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (isUrlRequired() && getUrl() == null) {
			throw new IllegalArgumentException("Property 'url' is required");
		}
	}

	/**
	 * Return whether the 'url' property is required.
	 * 返回是否需要'url'属性。
	 * <p>The default implementation returns {@code true}.
	 * This can be overridden in subclasses.
	 */
	protected boolean isUrlRequired() {
		return true;
	}

	/**
	 * Check whether the underlying resource that the configured URL points to
	 * actually exists.
	 * 检查配置的URL是否指向底层资源实际存在。
	 * @param locale the desired Locale that we're looking for
	 * @return {@code true} if the resource exists (or is assumed to exist);
	 * {@code false} if we know that it does not exist
	 * @throws Exception if the resource exists but is invalid (e.g. could not be parsed)
	 */
	public boolean checkResource(Locale locale) throws Exception {
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "; URL [" + getUrl() + "]";
	}

}
