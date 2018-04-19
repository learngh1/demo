var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":27,"id":1107,"methods":[{"el":14,"sc":5,"sl":9},{"el":21,"sc":5,"sl":16},{"el":26,"sc":5,"sl":23}],"name":"OrderItemModelTest","sl":8}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_44":{"methods":[{"sl":16}],"name":"testAmountNegativeValidatoinIfDiscount","pass":true,"statements":[{"sl":18},{"sl":19},{"sl":20}]},"test_62":{"methods":[{"sl":9}],"name":"testAmountPositiveValidatoinIfNotDiscount","pass":true,"statements":[{"sl":11},{"sl":12},{"sl":13}]},"test_68":{"methods":[{"sl":23}],"name":"testQuantityPositiveValidatoin","pass":true,"statements":[{"sl":25}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [62], [], [62], [62], [62], [], [], [44], [], [44], [44], [44], [], [], [68], [], [68], [], []]
