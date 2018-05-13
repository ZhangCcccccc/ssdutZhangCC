define({
    // ȫͼĬ�ϱ���
    // backgroundColor: 'rgba(0,0,0,0)',
    
    // Ĭ��ɫ��
    color: ['#e52c3c','#f7b1ab','#fa506c','#f59288','#f8c4d8',
            '#e54f5c','#f06d5c','#e54f80','#f29c9f','#eeb5b7'],

    // ֵ��
    dataRange: {
        color:['#e52c3c','#f7b1ab']//��ɫ 
    },

    
    // K��ͼĬ�ϲ���
    k: {
        // barWidth : null          // Ĭ������Ӧ
        // barMaxWidth : null       // Ĭ������Ӧ 
        itemStyle: {
            normal: {
                color: '#e52c3c',          // ���������ɫ
                color0: '#f59288',      // ���������ɫ
                lineStyle: {
                    width: 1,
                    color: '#e52c3c',   // ���߱߿���ɫ
                    color0: '#f59288'   // ���߱߿���ɫ
                }
            },
            emphasis: {
                // color: ����,
                // color0: ����
            }
        }
    },
    
    // ��ͼĬ�ϲ���
    pie: {
        itemStyle: {
            normal: {
                // color: ����,
                borderColor: '#fff',
                borderWidth: 1,
                label: {
                    show: true,
                    position: 'outer',
                  textStyle: {color: 'black'}
                    // textStyle: null      // Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                },
                labelLine: {
                    show: true,
                    length: 20,
                    lineStyle: {
                        // color: ����,
                        width: 1,
                        type: 'solid'
                    }
                }
            }
        }
    },
    
    map: {
        mapType: 'china',   // ��ʡ��mapType��ʱ��������
        mapLocation: {
            x : 'center',
            y : 'center'
            // width    // ����Ӧ
            // height   // ����Ӧ
        },
        showLegendSymbol : true,       // ��ʾͼ����ɫ��ʶ��ϵ�б�ʶ��СԲ�㣩������legendʱ��Ч
        itemStyle: {
            normal: {
                // color: ����,
                borderColor: '#fff',
                borderWidth: 1,
                areaStyle: {
                    color: '#ccc'//rgba(135,206,250,0.8)
                },
                label: {
                    show: false,
                    textStyle: {
                        color: 'rgba(139,69,19,1)'
                    }
                }
            },
            emphasis: {                 // Ҳ��ѡ����ʽ
                // color: ����,
                borderColor: 'rgba(0,0,0,0)',
                borderWidth: 1,
                areaStyle: {
                    color: '#f3f39d'
                },
                label: {
                    show: false,
                    textStyle: {
                        color: 'rgba(139,69,19,1)'
                    }
                }
            }
        }
    },
    
    force : {
        // �������������ʽ�Ḳ�ǽڵ�Ĭ����ʽ
        itemStyle: {
            normal: {
                // color: ����,
                label: {
                    show: false
                    // textStyle: null      // Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                },
                nodeStyle : {
                    brushType : 'both',
                    strokeColor : '#e54f5c'
                },
                linkStyle : {
                    strokeColor : '#e54f5c'
                }
            },
            emphasis: {
                // color: ����,
                label: {
                    show: false
                    // textStyle: null      // Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                },
                nodeStyle : {},
                linkStyle : {}
            }
        }
    },
    
    gauge : {
        axisLine: {            // ��������
            show: true,        // Ĭ����ʾ������show������ʾ���
            lineStyle: {       // ����lineStyle����������ʽ
                color: [[0.2, '#e52c3c'],[0.8, '#f7b1ab'],[1, '#fa506c']], 
                width: 8
            }
        },
        axisTick: {            // ������С���
            splitNumber: 10,   // ÿ��splitϸ�ֶ��ٶ�
            length :12,        // ����length�����߳�
            lineStyle: {       // ����lineStyle����������ʽ
                color: 'auto'
            }
        },
        axisLabel: {           // �������ı���ǩ�����axis.axisLabel
            textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                color: 'auto'
            }
        },
        splitLine: {           // �ָ���
            length : 18,         // ����length�����߳�
            lineStyle: {       // ����lineStyle�����lineStyle������������ʽ
                color: 'auto'
            }
        },
        pointer : {
            length : '90%',
            color : 'auto'
        },
        title : {
            textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                color: '#333'
            }
        },
        detail : {
            textStyle: {       // ��������Ĭ��ʹ��ȫ���ı���ʽ�����TEXTSTYLE
                color: 'auto'
            }
        }
    }
});