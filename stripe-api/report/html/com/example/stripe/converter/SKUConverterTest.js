var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":401,"id":1932,"methods":[{"el":36,"sc":5,"sl":29},{"el":43,"sc":5,"sl":38},{"el":50,"sc":5,"sl":45},{"el":57,"sc":5,"sl":52},{"el":64,"sc":5,"sl":59},{"el":72,"sc":5,"sl":66},{"el":80,"sc":5,"sl":74},{"el":90,"sc":5,"sl":82},{"el":99,"sc":5,"sl":92},{"el":156,"sc":5,"sl":101},{"el":166,"sc":5,"sl":158},{"el":176,"sc":5,"sl":168},{"el":186,"sc":5,"sl":178},{"el":196,"sc":5,"sl":188},{"el":210,"sc":5,"sl":198},{"el":223,"sc":5,"sl":212},{"el":237,"sc":5,"sl":225},{"el":250,"sc":5,"sl":239},{"el":263,"sc":5,"sl":252},{"el":288,"sc":5,"sl":265},{"el":342,"sc":5,"sl":290},{"el":400,"sc":5,"sl":344}],"name":"SKUConverterTest","sl":26}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":29},{"sl":74}],"name":"testRequiredInventoryTypeWhenInventoryValueIsSetValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":76},{"sl":77},{"sl":78},{"sl":79}]},"test_10":{"methods":[{"sl":29},{"sl":38}],"name":"testRequiredCurrencyValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":40},{"sl":41},{"sl":42}]},"test_19":{"methods":[{"sl":29},{"sl":82}],"name":"testInventoryTypeFiniteInvalidWhenInventoryValueIsSet","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":84},{"sl":85},{"sl":86},{"sl":87},{"sl":88},{"sl":89}]},"test_20":{"methods":[{"sl":188}],"name":"testConvertParamsToModelInventoryRequiredValidation","pass":true,"statements":[{"sl":190},{"sl":191},{"sl":192},{"sl":193},{"sl":195}]},"test_22":{"methods":[{"sl":290}],"name":"testConvertModelToParams","pass":true,"statements":[{"sl":292},{"sl":293},{"sl":294},{"sl":295},{"sl":296},{"sl":297},{"sl":298},{"sl":299},{"sl":300},{"sl":302},{"sl":303},{"sl":304},{"sl":305},{"sl":306},{"sl":307},{"sl":308},{"sl":310},{"sl":311},{"sl":312},{"sl":314},{"sl":315},{"sl":316},{"sl":317},{"sl":319},{"sl":321},{"sl":322},{"sl":323},{"sl":324},{"sl":325},{"sl":326},{"sl":327},{"sl":329},{"sl":330},{"sl":332},{"sl":333},{"sl":334},{"sl":335},{"sl":337},{"sl":338},{"sl":339},{"sl":340},{"sl":341}]},"test_23":{"methods":[{"sl":29},{"sl":59}],"name":"testRequiredProductValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":61},{"sl":62},{"sl":63}]},"test_28":{"methods":[{"sl":239}],"name":"testUnsupportedInventoryValueException","pass":true,"statements":[{"sl":241},{"sl":242},{"sl":243},{"sl":244},{"sl":245},{"sl":246},{"sl":247},{"sl":248},{"sl":249}]},"test_34":{"methods":[{"sl":225}],"name":"testInventoryValueLimited","pass":true,"statements":[{"sl":227},{"sl":228},{"sl":229},{"sl":230},{"sl":231},{"sl":232},{"sl":233},{"sl":234},{"sl":235},{"sl":236}]},"test_45":{"methods":[{"sl":29},{"sl":66}],"name":"testRequiredQuantityWhenTypeIsFiniteValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":68},{"sl":69},{"sl":70},{"sl":71}]},"test_48":{"methods":[{"sl":265}],"name":"testConvertModelToParamsWithoutAttributesAndWithEmptyIntentory","pass":true,"statements":[{"sl":267},{"sl":268},{"sl":269},{"sl":270},{"sl":272},{"sl":273},{"sl":274},{"sl":275},{"sl":276},{"sl":277},{"sl":279},{"sl":281},{"sl":282},{"sl":283},{"sl":284},{"sl":286},{"sl":287}]},"test_49":{"methods":[{"sl":101}],"name":"testConvertParamsToModel","pass":true,"statements":[{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":108},{"sl":109},{"sl":110},{"sl":111},{"sl":112},{"sl":113},{"sl":115},{"sl":116},{"sl":117},{"sl":118},{"sl":119},{"sl":120},{"sl":121},{"sl":122},{"sl":123},{"sl":124},{"sl":125},{"sl":126},{"sl":128},{"sl":129},{"sl":130},{"sl":131},{"sl":132},{"sl":133},{"sl":135},{"sl":136},{"sl":137},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":143},{"sl":144},{"sl":146},{"sl":147},{"sl":148},{"sl":149},{"sl":151},{"sl":152},{"sl":153},{"sl":154},{"sl":155}]},"test_5":{"methods":[{"sl":198}],"name":"testConvertParamsToModelWithoutIdAndAttributes","pass":true,"statements":[{"sl":200},{"sl":201},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209}]},"test_53":{"methods":[{"sl":212}],"name":"testInventoryTypeInfinite","pass":true,"statements":[{"sl":214},{"sl":215},{"sl":216},{"sl":217},{"sl":218},{"sl":219},{"sl":220},{"sl":221},{"sl":222}]},"test_56":{"methods":[{"sl":29},{"sl":92}],"name":"testInventoryTypeInfiniteInvalidWhenInventoryValueIsSet","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98}]},"test_59":{"methods":[{"sl":158}],"name":"testConvertParamsToModelCurrencyRequiredValidation","pass":true,"statements":[{"sl":160},{"sl":162},{"sl":163},{"sl":164},{"sl":165}]},"test_81":{"methods":[{"sl":168}],"name":"testConvertParamsToModelProductRequiredValidation","pass":true,"statements":[{"sl":170},{"sl":171},{"sl":173},{"sl":174},{"sl":175}]},"test_84":{"methods":[{"sl":252}],"name":"testUnsupportedInventoryTypeValiation","pass":true,"statements":[{"sl":254},{"sl":255},{"sl":256},{"sl":257},{"sl":258},{"sl":259},{"sl":261},{"sl":262}]},"test_85":{"methods":[{"sl":344}],"name":"testConvertEntityToModel","pass":true,"statements":[{"sl":346},{"sl":347},{"sl":348},{"sl":349},{"sl":350},{"sl":351},{"sl":352},{"sl":353},{"sl":354},{"sl":356},{"sl":357},{"sl":359},{"sl":360},{"sl":361},{"sl":362},{"sl":363},{"sl":364},{"sl":365},{"sl":366},{"sl":367},{"sl":368},{"sl":369},{"sl":370},{"sl":372},{"sl":373},{"sl":374},{"sl":375},{"sl":376},{"sl":377},{"sl":379},{"sl":381},{"sl":382},{"sl":383},{"sl":384},{"sl":385},{"sl":386},{"sl":388},{"sl":389},{"sl":391},{"sl":392},{"sl":393},{"sl":395},{"sl":396},{"sl":397},{"sl":398},{"sl":399}]},"test_89":{"methods":[{"sl":178}],"name":"testConvertParamsToModelPriceRequiredValidation","pass":true,"statements":[{"sl":180},{"sl":181},{"sl":182},{"sl":184},{"sl":185}]},"test_94":{"methods":[{"sl":29},{"sl":45}],"name":"testRequiredInventoryValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":47},{"sl":48},{"sl":49}]},"test_95":{"methods":[{"sl":29},{"sl":52}],"name":"testRequiredPriceValidation","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":35},{"sl":54},{"sl":55},{"sl":56}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [45, 56, 10, 1, 23, 95, 94, 19], [], [], [10], [], [10], [10], [10], [], [], [94], [], [94], [94], [94], [], [], [95], [], [95], [95], [95], [], [], [23], [], [23], [23], [23], [], [], [45], [], [45], [45], [45], [45], [], [], [1], [], [1], [1], [1], [1], [], [], [19], [], [19], [19], [19], [19], [19], [19], [], [], [56], [], [56], [56], [56], [56], [56], [], [], [49], [], [49], [49], [49], [49], [49], [49], [49], [49], [49], [49], [49], [], [49], [49], [49], [49], [49], [49], [49], [49], [49], [49], [49], [49], [], [49], [49], [49], [49], [49], [49], [], [49], [49], [49], [49], [49], [49], [49], [], [49], [49], [], [49], [49], [49], [49], [], [49], [49], [49], [49], [49], [], [], [59], [], [59], [], [59], [59], [59], [59], [], [], [81], [], [81], [81], [], [81], [81], [81], [], [], [89], [], [89], [89], [89], [], [89], [89], [], [], [20], [], [20], [20], [20], [20], [], [20], [], [], [5], [], [5], [5], [5], [5], [5], [5], [5], [5], [5], [5], [], [], [53], [], [53], [53], [53], [53], [53], [53], [53], [53], [53], [], [], [34], [], [34], [34], [34], [34], [34], [34], [34], [34], [34], [34], [], [], [28], [], [28], [28], [28], [28], [28], [28], [28], [28], [28], [], [], [84], [], [84], [84], [84], [84], [84], [84], [], [84], [84], [], [], [48], [], [48], [48], [48], [48], [], [48], [48], [48], [48], [48], [48], [], [48], [], [48], [48], [48], [48], [], [48], [48], [], [], [22], [], [22], [22], [22], [22], [22], [22], [22], [22], [22], [], [22], [22], [22], [22], [22], [22], [22], [], [22], [22], [22], [], [22], [22], [22], [22], [], [22], [], [22], [22], [22], [22], [22], [22], [22], [], [22], [22], [], [22], [22], [22], [22], [], [22], [22], [22], [22], [22], [], [], [85], [], [85], [85], [85], [85], [85], [85], [85], [85], [85], [], [85], [85], [], [85], [85], [85], [85], [85], [85], [85], [85], [85], [85], [85], [85], [], [85], [85], [85], [85], [85], [85], [], [85], [], [85], [85], [85], [85], [85], [85], [], [85], [85], [], [85], [85], [85], [], [85], [85], [85], [85], [85], [], []]
