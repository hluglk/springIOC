package peret;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassPathJSONApplicationContext implements BeanFactory {

    private List<Map<String, Object>> listMap;
    private Map<String, Object> sigletions = new HashMap<String, Object>();

    public ClassPathJSONApplicationContext(String path) {
        this.scanJson(path);
        this.instantiation();
    }

    private void instantiation() {
        for (Map<String, Object> map : listMap) {
            try {
                String beanId = (String) map.get("id");
                String clzss = (String) map.get("class");
                Object o = Class.forName(clzss).newInstance();
                sigletions.put(beanId, o);

//                    System.out.println(sigletions);

                List<Map<String,Object>> propertyList = (List<Map<String, Object>>) map.get("property");
                if (propertyList != null) {
                    for (Map<String, Object> property : propertyList) {
                        String name = (String) property.get("name");
                        String ref = (String) property.get("ref");
                        Object beanObj =this.sigletions.get(ref);

                        String methodName = "set" + name.substring(0, 1).toUpperCase()
                                + name.substring(1);
                        Method m = o.getClass().getMethod(methodName,
                                beanObj.getClass().getInterfaces()[0]);

                        m.invoke(o, beanObj);

//                            System.out.println(methodName);
                    }
                }


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void scanJson(String path) {
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null){
                sb.append(tempString + "\n");
                line++;
            }

            listMap = JSON.parseObject(sb.toString(), new TypeReference<List<Map<String,Object>>>(){});
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    @Override
    public Object getBean(String name) {
        return sigletions.get(name);
    }
}
