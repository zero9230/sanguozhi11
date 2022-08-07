
# 1 将目标目录下所有文件转化格式
将目标目录下所有的`.tex`文件都转化为`.md`文件，用于在obsidian阅读
目前应用于Math知识库

```shell
#!/bin/bash
read_dir(){
    local path="$1"
    local path=${path%/}

    for file in `ls -a "${path}"`
    do
        if [ -d "${path}/${file}" ]
        then
            if [[ $file != '.' && $file != '..' && file != '.git' && file != '.obsidian' ]]
            then
                echo "recursion $file"
                # `cd $file`
                read_dir "${path}/${file}"
                # `cd ..`
            fi
        elif echo "$file" | grep -q -E '\.tex$'	
            then	
                # extension="${file##*.}"
                filename="${file%.*}"
                echo "Converting $path/$file to $path/$filename.md"
                `pandoc -s $path"/"$file -o $path"/"$filename.md`
                # uncomment this line to delete the source file.
                # rm $file
        fi
    done
}
#测试目录 test
read_dir $1
```