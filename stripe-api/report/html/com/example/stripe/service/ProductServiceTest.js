var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":114,"id":1873,"methods":[{"el":27,"sc":5,"sl":23},{"el":33,"sc":5,"sl":29},{"el":49,"sc":5,"sl":35},{"el":66,"sc":5,"sl":51},{"el":82,"sc":5,"sl":68},{"el":104,"sc":5,"sl":84},{"el":113,"sc":5,"sl":106}],"name":"ProductServiceTest","sl":19}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_3":{"methods":[{"sl":35},{"sl":106}],"name":"testCreateProduct","pass":true,"statements":[{"sl":38},{"sl":40},{"sl":41},{"sl":42},{"sl":43},{"sl":45},{"sl":47},{"sl":48},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111}]},"test_31":{"methods":[{"sl":29}],"name":"testRequiredTypeValidation","pass":true,"statements":[{"sl":31},{"sl":32}]},"test_4":{"methods":[{"sl":51},{"sl":106}],"name":"testCreateProductUsingStripeAccountId","pass":true,"statements":[{"sl":54},{"sl":56},{"sl":57},{"sl":58},{"sl":59},{"sl":61},{"sl":62},{"sl":64},{"sl":65},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111}]},"test_52":{"methods":[{"sl":106}],"name":"testCreateOrderThenChangeShippingMethodThenPay_ConnectedStripeAcc","pass":true,"statements":[{"sl":107},{"sl":108},{"sl":109}]},"test_64":{"methods":[{"sl":106}],"name":"testCreateOrderThenChangeShippingMethodThenPay_MainAcc","pass":true,"statements":[{"sl":107},{"sl":108},{"sl":109}]},"test_73":{"methods":[{"sl":84},{"sl":106}],"name":"testUpdateProduct","pass":true,"statements":[{"sl":86},{"sl":87},{"sl":88},{"sl":89},{"sl":91},{"sl":92},{"sl":93},{"sl":95},{"sl":96},{"sl":97},{"sl":99},{"sl":101},{"sl":102},{"sl":103},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111}]},"test_87":{"methods":[{"sl":68},{"sl":106}],"name":"testRetrieveProduct","pass":true,"statements":[{"sl":70},{"sl":71},{"sl":72},{"sl":73},{"sl":75},{"sl":76},{"sl":77},{"sl":79},{"sl":80},{"sl":81},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [31], [], [31], [31], [], [], [3], [], [], [3], [], [3], [3], [3], [3], [], [3], [], [3], [3], [], [], [4], [], [], [4], [], [4], [4], [4], [4], [], [4], [4], [], [4], [4], [], [], [87], [], [87], [87], [87], [87], [], [87], [87], [87], [], [87], [87], [87], [], [], [73], [], [73], [73], [73], [73], [], [73], [73], [73], [], [73], [73], [73], [], [73], [], [73], [73], [73], [], [], [4, 87, 64, 52, 73, 3], [4, 87, 64, 52, 73, 3], [4, 87, 64, 52, 73, 3], [4, 87, 64, 52, 73, 3], [4, 87, 73, 3], [4, 87, 73, 3], [], [], []]
