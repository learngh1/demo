package com.example.test.api;

import org.apache.axis.encoding.Base64;
import ru.antinform.ExcelImage;
import ru.antinform.ExcelReaderUtil;
import com.example.api.CustomFieldValue;
import com.example.api.JiraApi;
import com.example.api.impl.CustomFieldValueImpl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by user on 16.10.2017.
 */
public class RestApiTest extends AbstractApiTest {

    private JiraApi restApi;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        restApi = createRestApi();
    }

    @Override
    protected JiraApi getApiForCreateIssue() {
        return restApi;
    }

    @Override
    protected String getSoapToken() {
        return "isnt used in rest";
    }

    @Override
    protected CustomFieldValue[] getCustomFieldValuesForCreateIssue() {
        return new CustomFieldValue[]{new CustomFieldValueImpl("environment", "", "test env")};
    }

    public void testPublishAttachment() throws Exception {
        Properties props = new Properties();
        props.put("findImages", "true");

        InputStream xls = getClass().getClassLoader().getResourceAsStream("tpl6.xls");

        Map<String, Object> res = ExcelReaderUtil.read(xls, props);
        List<ExcelImage> images = (List<ExcelImage>) res.get("images");

        ExcelImage image = images.get(0);
        String base64data = Base64.encode(image.getData());

        String[] base64dataArray = new String[]{base64data};
        String[] fileNames = new String[]{"testImage.png"};
        restApi.addBase64EncodedAttachmentsToIssue(null, getIssueKey(), fileNames, base64dataArray);

    }

    public void testAddComment() throws Exception {
        restApi.addComment(null, getIssueKey(), "comment from rest api test at " + new Date());
    }

    public void testGetServerInfo() throws Exception {
        restApi.getServerInfo(null);
    }
}
