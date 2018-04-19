var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":10,"id":173,"methods":[{"el":5,"sc":5,"sl":4},{"el":9,"sc":5,"sl":7}],"name":"InvalidProductTypeException","sl":3}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_16":{"methods":[{"sl":7}],"name":"testProductTypeValidation","pass":true,"statements":[{"sl":8}]},"test_88":{"methods":[{"sl":7}],"name":"testOneMoreProductTypeValidation","pass":true,"statements":[{"sl":8}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [88, 16], [88, 16], [], []]
