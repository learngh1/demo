var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":193,"id":2,"methods":[{"el":76,"sc":5,"sl":28},{"el":130,"sc":5,"sl":78},{"el":192,"sc":5,"sl":132}],"name":"OrderConverter","sl":22}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_15":{"methods":[{"sl":28}],"name":"testConvertModelToParamsWithOnlyCurrencyAndItems","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":38},{"sl":41},{"sl":44},{"sl":47},{"sl":50},{"sl":53},{"sl":56},{"sl":59},{"sl":63},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":72},{"sl":75}]},"test_2":{"methods":[{"sl":78}],"name":"testConvertParamsToModel","pass":true,"statements":[{"sl":80},{"sl":82},{"sl":83},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":92},{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":98},{"sl":101},{"sl":102},{"sl":104},{"sl":105},{"sl":107},{"sl":108},{"sl":110},{"sl":111},{"sl":114},{"sl":116},{"sl":117},{"sl":119},{"sl":120},{"sl":122},{"sl":123},{"sl":125},{"sl":129}]},"test_24":{"methods":[{"sl":78},{"sl":132}],"name":"testConvertEmptyEntityToModel","pass":true,"statements":[{"sl":80},{"sl":82},{"sl":85},{"sl":88},{"sl":92},{"sl":101},{"sl":104},{"sl":107},{"sl":110},{"sl":114},{"sl":116},{"sl":119},{"sl":122},{"sl":125},{"sl":129},{"sl":134},{"sl":136},{"sl":139},{"sl":142},{"sl":145},{"sl":148},{"sl":151},{"sl":154},{"sl":158},{"sl":160},{"sl":163},{"sl":167},{"sl":169},{"sl":177},{"sl":182},{"sl":191}]},"test_36":{"methods":[{"sl":28}],"name":"testAllowedParamValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":38},{"sl":41},{"sl":42},{"sl":44},{"sl":47},{"sl":50},{"sl":53},{"sl":56},{"sl":59},{"sl":63},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":72},{"sl":75}]},"test_40":{"methods":[{"sl":28}],"name":"testConvertModelToParams","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":36},{"sl":38},{"sl":39},{"sl":41},{"sl":42},{"sl":44},{"sl":45},{"sl":47},{"sl":48},{"sl":50},{"sl":51},{"sl":53},{"sl":54},{"sl":56},{"sl":57},{"sl":59},{"sl":63},{"sl":64},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":72},{"sl":75}]},"test_43":{"methods":[{"sl":78},{"sl":132}],"name":"testConvertEntityToModel","pass":true,"statements":[{"sl":80},{"sl":82},{"sl":83},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":92},{"sl":101},{"sl":102},{"sl":104},{"sl":105},{"sl":107},{"sl":108},{"sl":110},{"sl":111},{"sl":114},{"sl":116},{"sl":117},{"sl":119},{"sl":122},{"sl":123},{"sl":125},{"sl":129},{"sl":134},{"sl":136},{"sl":137},{"sl":139},{"sl":140},{"sl":142},{"sl":143},{"sl":145},{"sl":146},{"sl":148},{"sl":149},{"sl":151},{"sl":152},{"sl":154},{"sl":155},{"sl":158},{"sl":160},{"sl":161},{"sl":163},{"sl":164},{"sl":167},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":174},{"sl":177},{"sl":178},{"sl":179},{"sl":182},{"sl":191}]},"test_52":{"methods":[{"sl":28},{"sl":78},{"sl":132}],"name":"testCreateOrderThenChangeShippingMethodThenPay_ConnectedStripeAcc","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":38},{"sl":41},{"sl":44},{"sl":47},{"sl":50},{"sl":51},{"sl":53},{"sl":54},{"sl":56},{"sl":59},{"sl":63},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":72},{"sl":75},{"sl":80},{"sl":82},{"sl":83},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":92},{"sl":101},{"sl":102},{"sl":104},{"sl":107},{"sl":108},{"sl":110},{"sl":111},{"sl":114},{"sl":116},{"sl":117},{"sl":119},{"sl":122},{"sl":123},{"sl":125},{"sl":129},{"sl":134},{"sl":136},{"sl":137},{"sl":139},{"sl":140},{"sl":142},{"sl":143},{"sl":145},{"sl":146},{"sl":148},{"sl":151},{"sl":152},{"sl":154},{"sl":155},{"sl":158},{"sl":160},{"sl":161},{"sl":163},{"sl":164},{"sl":167},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":174},{"sl":177},{"sl":178},{"sl":179},{"sl":182},{"sl":183},{"sl":184},{"sl":185},{"sl":186},{"sl":187},{"sl":191}]},"test_58":{"methods":[{"sl":28}],"name":"testConvertModelToParamsUnsupportedShippingMethodException","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":38},{"sl":41},{"sl":44},{"sl":47},{"sl":50},{"sl":53},{"sl":56},{"sl":59},{"sl":60}]},"test_64":{"methods":[{"sl":28},{"sl":78},{"sl":132}],"name":"testCreateOrderThenChangeShippingMethodThenPay_MainAcc","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":35},{"sl":38},{"sl":41},{"sl":44},{"sl":47},{"sl":50},{"sl":51},{"sl":53},{"sl":56},{"sl":59},{"sl":63},{"sl":67},{"sl":68},{"sl":69},{"sl":70},{"sl":72},{"sl":75},{"sl":80},{"sl":82},{"sl":83},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":92},{"sl":101},{"sl":102},{"sl":104},{"sl":107},{"sl":108},{"sl":110},{"sl":111},{"sl":114},{"sl":116},{"sl":117},{"sl":119},{"sl":122},{"sl":123},{"sl":125},{"sl":129},{"sl":134},{"sl":136},{"sl":137},{"sl":139},{"sl":140},{"sl":142},{"sl":143},{"sl":145},{"sl":146},{"sl":148},{"sl":151},{"sl":152},{"sl":154},{"sl":155},{"sl":158},{"sl":160},{"sl":161},{"sl":163},{"sl":164},{"sl":167},{"sl":169},{"sl":170},{"sl":171},{"sl":172},{"sl":174},{"sl":177},{"sl":178},{"sl":179},{"sl":182},{"sl":183},{"sl":184},{"sl":185},{"sl":186},{"sl":187},{"sl":191}]},"test_7":{"methods":[{"sl":78}],"name":"testConvertEmptyParamsToModel","pass":true,"statements":[{"sl":80},{"sl":82},{"sl":85},{"sl":88},{"sl":92},{"sl":101},{"sl":104},{"sl":107},{"sl":110},{"sl":114},{"sl":116},{"sl":119},{"sl":122},{"sl":125},{"sl":129}]},"test_76":{"methods":[{"sl":78}],"name":"testConvertParamsToModelUnsupportedShippingMethodException","pass":true,"statements":[{"sl":80},{"sl":82},{"sl":85},{"sl":88},{"sl":92},{"sl":101},{"sl":104},{"sl":107},{"sl":110},{"sl":114},{"sl":116},{"sl":119},{"sl":122},{"sl":125},{"sl":126}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [64, 40, 52, 58, 15, 36], [], [64, 40, 52, 58, 15, 36], [64, 40, 52, 58, 15, 36], [64, 40, 52, 58, 15, 36], [], [], [64, 40, 52, 58, 15, 36], [40], [], [64, 40, 52, 58, 15, 36], [40], [], [64, 40, 52, 58, 15, 36], [40, 36], [], [64, 40, 52, 58, 15, 36], [40], [], [64, 40, 52, 58, 15, 36], [40], [], [64, 40, 52, 58, 15, 36], [64, 40, 52], [], [64, 40, 52, 58, 15, 36], [40, 52], [], [64, 40, 52, 58, 15, 36], [40], [], [64, 40, 52, 58, 15, 36], [58], [], [], [64, 40, 52, 15, 36], [40], [], [], [64, 40, 52, 15, 36], [64, 40, 52, 15, 36], [64, 40, 52, 15, 36], [64, 40, 52, 15, 36], [], [64, 40, 52, 15, 36], [], [], [64, 40, 52, 15, 36], [], [], [64, 7, 76, 52, 43, 2, 24], [], [64, 7, 76, 52, 43, 2, 24], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [], [64, 7, 76, 52, 43, 2, 24], [2], [2], [2], [2], [], [2], [], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [43, 2], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [], [64, 7, 76, 52, 43, 2, 24], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [2], [], [64, 7, 76, 52, 43, 2, 24], [64, 52, 43, 2], [], [64, 7, 76, 52, 43, 2, 24], [76], [], [], [64, 7, 52, 43, 2, 24], [], [], [64, 52, 43, 24], [], [64, 52, 43, 24], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [43], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [64, 52, 43], [], [], [64, 52, 43, 24], [], [64, 52, 43, 24], [64, 52, 43], [], [64, 52, 43, 24], [64, 52, 43], [], [], [64, 52, 43, 24], [], [64, 52, 43, 24], [64, 52, 43], [64, 52, 43], [64, 52, 43], [], [64, 52, 43], [], [], [64, 52, 43, 24], [64, 52, 43], [64, 52, 43], [], [], [64, 52, 43, 24], [64, 52], [64, 52], [64, 52], [64, 52], [64, 52], [], [], [], [64, 52, 43, 24], [], []]
