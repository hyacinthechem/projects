#ifndef ROLE_H
#define ROLE_H

#include <vector>
#include <string>

class Role {
private:
    std::vector<std::string> roleCall;

public:
    void createRoleCall();
    void generateRole(const std::vector<std::string>& firstName, const std::vector<std::string>& secondName); // Removed 'const'
    void shortenedRole(const std::vector<std::string>& names); // Removed 'const'
};

#endif // ROLE_H
