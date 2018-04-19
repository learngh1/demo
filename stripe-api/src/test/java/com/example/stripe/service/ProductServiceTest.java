package com.example.stripe.service;

import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.api.service.ProductService;
import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.impl.model.StripeAuthProviderImpl;
import com.example.stripe.impl.model.product.ProductModelImpl;
import com.example.stripe.stripe_depended.service.ProductServiceImpl;
import com.example.stripe.TestTokens;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductServiceTest {
    private static ProductService service;
    private static StripeAuthProvider authProvider;

    @BeforeClass
    public static void init() {
        service = new ProductServiceImpl();
        authProvider = new StripeAuthProviderImpl(TestTokens.MAIN_ACC_TEST_API_SECRET_KEY);
    }

    @Test(expected = FieldRequiredException.class)
    public void testRequiredTypeValidation() throws Exception {
        ProductModel model = new ProductModelImpl("name sample ");
        service.create(model, authProvider);
    }

    @Test
    public void testCreateProduct() throws Exception {
        //var i is used to distinguish created objects in stripe dashboard
        int i = 2;

        ProductModel model = new ProductModelImpl("name sample " + i);
        model.setType(ProductType.GOOD);
        model.setDescription("desc sample " + i);
        model.setAttributes(new ArrayList<>(Arrays.asList("size", "color")));

        ProductModel res = service.create(model, authProvider);

        Assert.assertNotNull(res.getId());
        assertEqualsModels(model, res);
    }

    @Test
    public void testCreateProductUsingStripeAccountId() throws Exception {
        //var i is used to distinguish created objects in stripe dashboard
        int i = 5;

        ProductModel model = new ProductModelImpl("name sample " + i);
        model.setType(ProductType.GOOD);
        model.setDescription("desc sample " + i);
        model.setAttributes(new ArrayList<>(Arrays.asList("size", "color")));

        StripeAuthProvider authContainer = new StripeAuthProviderImpl(authProvider.getApiKey(), TestTokens.PROMOTER_ONE_ACC_CONNECTED_STRIPE_USER_ID);
        ProductModel res = service.create(model, authContainer);

        Assert.assertNotNull(res.getId());
        assertEqualsModels(model, res);
    }

    @Test
    public void testRetrieveProduct() throws Exception {
        ProductModel model = new ProductModelImpl("product for update");
        model.setType(ProductType.GOOD);
        model.setDescription("desc ");
        model.setAttributes(new ArrayList<>(Arrays.asList("width", "color")));

        ProductModel res = service.create(model, authProvider);
        Assert.assertNotNull(res);
        Assert.assertNotNull(res.getId());

        ProductModel retrievedModel = service.retrieve(res.getId(), authProvider);
        Assert.assertNotNull(retrievedModel);
        assertEqualsModels(model, retrievedModel);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductModel model = new ProductModelImpl("product for update");
        model.setType(ProductType.GOOD);
        model.setDescription("desc ");
        model.setAttributes(new ArrayList<>(Arrays.asList("width", "color")));

        model = service.create(model, authProvider);
        Assert.assertNotNull(model);
        Assert.assertNotNull(model.getId());

        model.setName("updated name");
        model.setDescription("updated desc");
        model.getAttributes().add("added attribute");

        service.update(model, authProvider);

        ProductModel retrievedModel = service.retrieve(model.getId(), authProvider);
        Assert.assertNotNull(retrievedModel);
        assertEqualsModels(model, retrievedModel);
    }

    public static void assertEqualsModels(ProductModel model_1, ProductModel model_2) {
        Assert.assertEquals(model_1.getName(), model_2.getName());
        Assert.assertEquals(model_1.getDescription(), model_2.getDescription());
        if (model_1.getAttributes() != null) {
            Assert.assertEquals(model_1.getAttributes().size(), model_2.getAttributes().size());
            Assert.assertTrue(model_2.getAttributes().containsAll(model_1.getAttributes()));
        }
    }
}
