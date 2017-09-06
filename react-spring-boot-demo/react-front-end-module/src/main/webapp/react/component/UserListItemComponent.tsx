import * as React from 'react'

interface UserListItemProps {
    id: number,
    name: string,
    desc: string,
    onShowDetailsClickHandler: (id: number) => void
}

export const UserListItemComponent = (props: UserListItemProps) => {
    return (
        <tr>
            <td>{props.id}</td>
            <td>{props.name}</td>
            <td>{props.desc}</td>
            <td><button onClick={(e: React.SyntheticEvent<HTMLButtonElement>) => props.onShowDetailsClickHandler(props.id)}>show details</button></td>
        </tr>
    )
}