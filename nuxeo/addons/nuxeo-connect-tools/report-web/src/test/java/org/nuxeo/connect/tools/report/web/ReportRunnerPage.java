/*
 * (C) Copyright 2012-2013 Nuxeo SA (http://nuxeo.com/) and others.
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
 *
 */
package org.nuxeo.connect.tools.report.web;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.nuxeo.runtime.test.runner.web.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

/**
 *
 *
 */
public class ReportRunnerPage extends WebPage {

    @FindBy(how = How.NAME, using = "reports")
    protected final WebElement reports = null;

    public void select(String... options) {
        final Select selector = new Select(reports);
        selector.deselectAll();
        Arrays.stream(options).forEach(selector::selectByValue);
    }

    public Set<String> findOptions() {
        return new Select(reports).getOptions().stream().map(WebElement::getText).collect(Collectors.toSet());
    }

    public Set<String> findSelectedOptions() {
        return new Select(reports).getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toSet());
    }

    public ReportRunnerPage submit() {
        findElement(By.xpath("//input")).submit();
        return getPage(ReportRunnerPage.class);
    }
}
