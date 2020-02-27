# file-header-utils
文件头校验工具

## 用途
用于文件上传时，校验文件的文件头和文件类型是否一致。  

## 目的
防止用户上传恶意脚本、病毒或木马文件，保证系统安全性，限制系统中文件的上传类型。

## 注意事项
1. **文件头校验并非完全可靠**，因为文件头与文件类型是多对多的关系。如：  
zip：504b0304、504b0506；  
504b0304：zip、docx、pptx；  
另外有些文件如txt没有文件头.
2. 由于收录的关系，不能保证完全覆盖，故校验相对宽松，以防止常见类型无法通过校验，影响正常使用。  
建议直接复制FileHeaderUtil类到指定项目中，根据需求自定义修改后使用。可根据参考来源中收录数据查找所需文件头值。

## 参考来源
文件头值参考来源  
* FileSignatures：https://www.filesignatures.net/index.php 
* NiceTool：http://www.nicetool.net/embed/file_signature.html

