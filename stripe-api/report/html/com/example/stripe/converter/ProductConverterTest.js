var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":191,"id":1326,"methods":[{"el":31,"sc":5,"sl":28},{"el":38,"sc":5,"sl":33},{"el":49,"sc":5,"sl":40},{"el":60,"sc":5,"sl":51},{"el":66,"sc":5,"sl":62},{"el":90,"sc":5,"sl":68},{"el":121,"sc":5,"sl":92},{"el":129,"sc":5,"sl":123},{"el":144,"sc":5,"sl":131},{"el":182,"sc":5,"sl":146},{"el":190,"sc":5,"sl":184}],"name":"ProductConverterTest","sl":25}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_11":{"methods":[{"sl":92}],"name":"testConvertFromStripeProductToModel","pass":true,"statements":[{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":99},{"sl":100},{"sl":101},{"sl":102},{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":108},{"sl":109},{"sl":111},{"sl":112},{"sl":113},{"sl":114},{"sl":115},{"sl":116},{"sl":117},{"sl":118},{"sl":119},{"sl":120}]},"test_16":{"methods":[{"sl":33}],"name":"testProductTypeValidation","pass":true,"statements":[{"sl":35},{"sl":36},{"sl":37}]},"test_29":{"methods":[{"sl":131}],"name":"testConvertFromParamsToModelWithouDescriptionAndAttributes","pass":true,"statements":[{"sl":133},{"sl":134},{"sl":135},{"sl":136},{"sl":137},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":142},{"sl":143}]},"test_30":{"methods":[{"sl":68}],"name":"testConvertFromModelToParams","pass":true,"statements":[{"sl":70},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":79},{"sl":80},{"sl":81},{"sl":82},{"sl":83},{"sl":84},{"sl":85},{"sl":86},{"sl":87},{"sl":88},{"sl":89}]},"test_32":{"methods":[{"sl":62}],"name":"testConvertParamsToModelWithoutName","pass":true,"statements":[{"sl":64},{"sl":65}]},"test_33":{"methods":[{"sl":28}],"name":"testRequiredNameValidation","pass":true,"statements":[{"sl":30}]},"test_63":{"methods":[{"sl":51}],"name":"testAttributesPresentInResultParams","pass":true,"statements":[{"sl":53},{"sl":54},{"sl":55},{"sl":56},{"sl":57},{"sl":58},{"sl":59}]},"test_75":{"methods":[{"sl":123}],"name":"testConvertEmptyEntityToModel","pass":true,"statements":[{"sl":125},{"sl":126},{"sl":127},{"sl":128}]},"test_88":{"methods":[{"sl":184}],"name":"testOneMoreProductTypeValidation","pass":true,"statements":[{"sl":186},{"sl":187},{"sl":188},{"sl":189}]},"test_90":{"methods":[{"sl":40}],"name":"testDescriptionPresentInResultParams","pass":true,"statements":[{"sl":42},{"sl":43},{"sl":44},{"sl":45},{"sl":46},{"sl":47},{"sl":48}]},"test_96":{"methods":[{"sl":146}],"name":"testConvertFromParamsToModel","pass":true,"statements":[{"sl":148},{"sl":149},{"sl":150},{"sl":151},{"sl":152},{"sl":153},{"sl":155},{"sl":157},{"sl":158},{"sl":159},{"sl":160},{"sl":161},{"sl":162},{"sl":163},{"sl":164},{"sl":165},{"sl":166},{"sl":167},{"sl":168},{"sl":170},{"sl":171},{"sl":172},{"sl":173},{"sl":174},{"sl":175},{"sl":176},{"sl":177},{"sl":178},{"sl":179},{"sl":180},{"sl":181}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [33], [], [33], [], [], [16], [], [16], [16], [16], [], [], [90], [], [90], [90], [90], [90], [90], [90], [90], [], [], [63], [], [63], [63], [63], [63], [63], [63], [63], [], [], [32], [], [32], [32], [], [], [30], [], [30], [30], [30], [30], [30], [30], [30], [30], [], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [30], [], [], [11], [], [11], [11], [11], [11], [], [11], [11], [11], [11], [11], [11], [11], [11], [11], [11], [11], [], [11], [11], [11], [11], [11], [11], [11], [11], [11], [11], [], [], [75], [], [75], [75], [75], [75], [], [], [29], [], [29], [29], [29], [29], [29], [29], [29], [29], [29], [29], [29], [], [], [96], [], [96], [96], [96], [96], [96], [96], [], [96], [], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [96], [], [], [88], [], [88], [88], [88], [88], [], []]