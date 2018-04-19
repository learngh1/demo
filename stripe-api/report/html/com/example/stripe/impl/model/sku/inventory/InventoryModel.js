var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":41,"id":427,"methods":[{"el":10,"sc":5,"sl":10},{"el":16,"sc":5,"sl":12},{"el":20,"sc":5,"sl":18},{"el":24,"sc":5,"sl":22},{"el":28,"sc":5,"sl":26},{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34},{"el":40,"sc":5,"sl":38}],"name":"InventoryModel","sl":5}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":10},{"sl":26},{"sl":34},{"sl":38}],"name":"testRequiredInventoryTypeWhenInventoryValueIsSetValidation","pass":true,"statements":[{"sl":27},{"sl":35},{"sl":39}]},"test_10":{"methods":[{"sl":10}],"name":"testRequiredCurrencyValidation","pass":true,"statements":[]},"test_18":{"methods":[{"sl":10},{"sl":22},{"sl":30}],"name":"testProductRequiredValidation","pass":true,"statements":[{"sl":23},{"sl":31}]},"test_19":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testInventoryTypeFiniteInvalidWhenInventoryValueIsSet","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_21":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34}],"name":"testCreateSKUUsingStripeAccountId","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35}]},"test_22":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testConvertModelToParams","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_23":{"methods":[{"sl":10}],"name":"testRequiredProductValidation","pass":true,"statements":[]},"test_28":{"methods":[{"sl":10},{"sl":30}],"name":"testUnsupportedInventoryValueException","pass":true,"statements":[{"sl":31}]},"test_34":{"methods":[{"sl":10},{"sl":30},{"sl":34},{"sl":38}],"name":"testInventoryValueLimited","pass":true,"statements":[{"sl":31},{"sl":35},{"sl":39}]},"test_38":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34}],"name":"testRetrieveSKU","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35}]},"test_45":{"methods":[{"sl":10},{"sl":18},{"sl":26},{"sl":30}],"name":"testRequiredQuantityWhenTypeIsFiniteValidation","pass":true,"statements":[{"sl":19},{"sl":27},{"sl":31}]},"test_47":{"methods":[{"sl":10},{"sl":22},{"sl":30}],"name":"testInventoryRequiredValidation","pass":true,"statements":[{"sl":23},{"sl":31}]},"test_48":{"methods":[{"sl":10},{"sl":18},{"sl":26},{"sl":34}],"name":"testConvertModelToParamsWithoutAttributesAndWithEmptyIntentory","pass":true,"statements":[{"sl":19},{"sl":27},{"sl":35}]},"test_49":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testConvertParamsToModel","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_5":{"methods":[{"sl":10}],"name":"testConvertParamsToModelWithoutIdAndAttributes","pass":true,"statements":[]},"test_52":{"methods":[{"sl":10},{"sl":18},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testCreateOrderThenChangeShippingMethodThenPay_ConnectedStripeAcc","pass":true,"statements":[{"sl":19},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_53":{"methods":[{"sl":10},{"sl":26},{"sl":30}],"name":"testInventoryTypeInfinite","pass":true,"statements":[{"sl":27},{"sl":31}]},"test_56":{"methods":[{"sl":10},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testInventoryTypeInfiniteInvalidWhenInventoryValueIsSet","pass":true,"statements":[{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_57":{"methods":[{"sl":10},{"sl":22},{"sl":30}],"name":"testCurrencyRequiredValidation","pass":true,"statements":[{"sl":23},{"sl":31}]},"test_6":{"methods":[{"sl":10},{"sl":22},{"sl":30}],"name":"testPriceRequiredValidation","pass":true,"statements":[{"sl":23},{"sl":31}]},"test_64":{"methods":[{"sl":10},{"sl":18},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testCreateOrderThenChangeShippingMethodThenPay_MainAcc","pass":true,"statements":[{"sl":19},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_65":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34}],"name":"testCreateSKU","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35}]},"test_84":{"methods":[{"sl":10}],"name":"testUnsupportedInventoryTypeValiation","pass":true,"statements":[]},"test_85":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testConvertEntityToModel","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_93":{"methods":[{"sl":10},{"sl":18},{"sl":22},{"sl":26},{"sl":30},{"sl":34},{"sl":38}],"name":"testUpdateSKU","pass":true,"statements":[{"sl":19},{"sl":23},{"sl":27},{"sl":31},{"sl":35},{"sl":39}]},"test_94":{"methods":[{"sl":10}],"name":"testRequiredInventoryValidation","pass":true,"statements":[]},"test_95":{"methods":[{"sl":10}],"name":"testRequiredPriceValidation","pass":true,"statements":[]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [65, 34, 85, 45, 49, 6, 56, 38, 10, 1, 64, 47, 23, 52, 95, 22, 93, 57, 53, 28, 48, 84, 18, 94, 19, 21, 5], [], [], [], [], [], [], [], [65, 85, 45, 49, 38, 64, 52, 22, 93, 48, 19, 21], [65, 85, 45, 49, 38, 64, 52, 22, 93, 48, 19, 21], [], [], [65, 85, 49, 6, 38, 47, 22, 93, 57, 18, 19, 21], [65, 85, 49, 6, 38, 47, 22, 93, 57, 18, 19, 21], [], [], [65, 85, 45, 49, 56, 38, 1, 64, 52, 22, 93, 53, 48, 19, 21], [65, 85, 45, 49, 56, 38, 1, 64, 52, 22, 93, 53, 48, 19, 21], [], [], [65, 34, 85, 45, 49, 6, 56, 38, 64, 47, 52, 22, 93, 57, 53, 28, 18, 19, 21], [65, 34, 85, 45, 49, 6, 56, 38, 64, 47, 52, 22, 93, 57, 53, 28, 18, 19, 21], [], [], [65, 34, 85, 49, 56, 38, 1, 64, 52, 22, 93, 48, 19, 21], [65, 34, 85, 49, 56, 38, 1, 64, 52, 22, 93, 48, 19, 21], [], [], [34, 85, 49, 56, 1, 64, 52, 22, 93, 19], [34, 85, 49, 56, 1, 64, 52, 22, 93, 19], [], []]