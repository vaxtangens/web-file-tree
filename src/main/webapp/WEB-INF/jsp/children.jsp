<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${children}" var="node">
     <li id="${node.getId()}" draggable="true" ondragstart="drag(event)"
          ondragover="allowDrop(event)" ondrop="drop(event, ${node.getId()})">

               <table>
                    <tr>
                         <div class="dropdown">

                              <td id="${node.getId()}" tabindex="0" class="folder"
                                   onclick="showDropOut('dropList${node.getId()}')"
                                   ondblclick="loadChildren(${node.getId()})"></td>
                              <td id="name${node.getName()}">${node.getName()}</td>

                              <div id="dropList${node.getId()}" class="dropdown-content">
                                   <button onclick="showHideElement('addForm${node.getId()}')">Add
                                        new folder</button>
                                   <button onclick="showHideElement('updateForm${node.getId()}')">Rename</button>
                                   <button onclick="showHideElement('deleteForm${node.getId()}')">Delete</button>
                              </div>

                              <div id="addForm${node.getId()}" class="hidden">
                                   <form>
                                        Folder name: <input id="folderName${node.getId()}"
                                             type="text" value="New folder"> <input
                                             type="submit" value="Accept"
                                             onclick="createNode(${node.getId()})">
                                   </form>
                              </div>

                              <div id="updateForm${node.getId()}" class="hidden">
                                   <form>
                                        New folder name: <input id="newFolderName${node.getId()}"
                                             type="text" value="${node.getName()}"> <input
                                             type="submit" value="Accept"
                                             onclick="updateNode(${node.getId()}, ${node.getParent().getId()})">
                                   </form>
                              </div>

                              <div id="deleteForm${node.getId()}" class="hidden">
                                   <form>
                                        Confirm delete? <input type="submit" value="YES"
                                             onclick="deleteNode(${node.getId()}, ${node.getParent().getId()})">
                                        <input type="submit" value="NO"
                                             onclick="showHideElement('deleteForm${node.getId()}')">
                                   </form>
                              </div>

                         </div>
                    </tr>
               </table>

               <ul id="children${node.getId()}">
               </ul>
     </li>
</c:forEach>
